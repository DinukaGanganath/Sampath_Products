package com.sampathproducts.PurchaseOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderDao dao;

    /*
     * public PurchaseOrderController(PurchaseOrderDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/purchaseorder")
    public ModelAndView purchaseorderUI() {
        ModelAndView viewPurchaseOrder = new ModelAndView();
        viewPurchaseOrder.setViewName("PurchaseOrder/PurchaseOrder.html");
        return viewPurchaseOrder;
    }

    @RequestMapping(value = "/purchaseorderadd")
    public ModelAndView purchaseorderAddUI() {
        ModelAndView viewPurchaseOrderAdd = new ModelAndView();
        viewPurchaseOrderAdd.setViewName("PurchaseOrder/PurchaseOrderAdd.html");
        return viewPurchaseOrderAdd;
    }

    @RequestMapping(value = "/purchaseorderdeleted")
    public ModelAndView purchaseorderDeletedItems() {
        ModelAndView viewPurchaseOrderAdd = new ModelAndView();
        viewPurchaseOrderAdd.setViewName("PurchaseOrder/PurchaseOrderDeleted.html");
        return viewPurchaseOrderAdd;
    }

    @RequestMapping(value = "/purchaseorderedit")
    public ModelAndView purchaseorderEditUI() {
        ModelAndView viewPurchaseOrderEdit = new ModelAndView();
        viewPurchaseOrderEdit.setViewName("PurchaseOrder/PurchaseOrderEdit.html");
        return viewPurchaseOrderEdit;
    }

    @RequestMapping(value = "/purchaseorderview")
    public ModelAndView purchaseorderViewUI() {
        ModelAndView viewPurchaseOrderView = new ModelAndView();
        viewPurchaseOrderView.setViewName("PurchaseOrder/PurchaseOrderView.html");
        return viewPurchaseOrderView;
    }

    // get database values as json data
    @GetMapping(value = "/purchaseorder/findall", produces = "application/json")
    public List<PurchaseOrder> findAll() {
        return dao.findAll(Sort.by(Direction.DESC, "purchaseorder_id"));
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

            dao.save(purchaseorder);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

}
