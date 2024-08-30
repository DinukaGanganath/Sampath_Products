package com.sampathproducts.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.Email.EmailDetails;
import com.sampathproducts.Email.EmailService;
import com.sampathproducts.PurchaseOrderHasQuotation.PurchaseOrderHasQuotation;
import com.sampathproducts.Request.Request;
import com.sampathproducts.Supplier.Supplier;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderDao dao;

    @Autowired
    private EmailService emailServiceImpl;

    @RequestMapping(value = "/purchaseorder")
    public ModelAndView purchaseorderUI() {
        ModelAndView viewPurchaseOrder = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPurchaseOrder.addObject("logusername", auth.getName());
        viewPurchaseOrder.setViewName("PurchaseOrder/PurchaseOrder.html");
        return viewPurchaseOrder;
    }

    @RequestMapping(value = "/purchaseorderadd")
    public ModelAndView purchaseorderAddUI() {
        ModelAndView viewPurchaseOrderAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPurchaseOrderAdd.addObject("logusername", auth.getName());
        viewPurchaseOrderAdd.setViewName("PurchaseOrder/PurchaseOrderAdd.html");
        return viewPurchaseOrderAdd;
    }

    @RequestMapping(value = "/purchaseorderdeleted")
    public ModelAndView purchaseorderDeletedItems() {
        ModelAndView viewPurchaseOrderAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPurchaseOrderAdd.addObject("logusername", auth.getName());
        viewPurchaseOrderAdd.setViewName("PurchaseOrder/PurchaseOrderDeleted.html");
        return viewPurchaseOrderAdd;
    }

    @RequestMapping(value = "/purchaseorderedit")
    public ModelAndView purchaseorderEditUI() {
        ModelAndView viewPurchaseOrderEdit = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPurchaseOrderEdit.addObject("logusername", auth.getName());
        viewPurchaseOrderEdit.setViewName("PurchaseOrder/PurchaseOrderEdit.html");
        return viewPurchaseOrderEdit;
    }

    @RequestMapping(value = "/purchaseorderview")
    public ModelAndView purchaseorderViewUI() {
        ModelAndView viewPurchaseOrderView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPurchaseOrderView.addObject("logusername", auth.getName());
        viewPurchaseOrderView.setViewName("PurchaseOrder/PurchaseOrderView.html");
        return viewPurchaseOrderView;
    }

    // get database values as json data
    @GetMapping(value = "/purchaseorder/findall", produces = "application/json")
    public List<PurchaseOrder> getAllData() {
        return dao.findAll();
    }

    // get database values as json data
    @GetMapping(value = "/purchaseorder/findall/deleted", produces = "application/json")
    public List<PurchaseOrder> findAllDeleted() {
        return dao.getDeletedPurchaseOrder();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/purchaseorder/findall/exist", produces = "application/json")
    public List<PurchaseOrder> findAllExist() {
        return dao.getExistingPurchaseOrder();
    }

    // Save a PurchaseOrder with post method
    @PostMapping(value = "/purchaseorder/save")
    public String save(@RequestBody PurchaseOrder purchaseorder) {

        try {
            purchaseorder.setPurchase_created_date(LocalDateTime.now());
            for (PurchaseOrderHasQuotation phq : purchaseorder.getPurchaseOrderHasQuotationList()) {
                phq.setPurchase_order_id(purchaseorder);
            }
            dao.save(purchaseorder);

            List<PurchaseOrderHasQuotation> list = purchaseorder.getPurchaseOrderHasQuotationList();
            for (PurchaseOrderHasQuotation phq : list) {
                Request request = phq.getRequest_id();
                Supplier supplier = request.getSupplier_id();
                String business = supplier.getSupplier_business_name();
                String material_name = supplier.getSupplier_material_id().getMaterial_name();
                String material_unit = supplier.getSupplier_material_id().getMaterial_unit();
                Integer req_qty = phq.getRequested_qty();
                LocalDateTime req_date = request.getRequest_created_date();
                Integer req_unit = request.getRequest_price() / request.getRequest_units();
                String email = supplier.getSupplier_email();

                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setSendTo(email);
                emailDetails.setMsgBody("To " + business.replace('_', ' ')
                        + ", \n\n\t According to the Quotation you have sent us on " + req_date
                        + ", we are pleased to purchase " + req_qty + material_unit + "s of " + material_name
                        + " at your unit price, Rs. " + req_unit
                        + ". \n\n\t Full payment for the Products will be paid on the purchasing date. \n\n\tYour sincerly,\nSampath Products.");
                emailDetails.setSubject("Purchase order for buying " + material_name);

                emailServiceImpl.sendSimpleMail(emailDetails);

            }

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

}
