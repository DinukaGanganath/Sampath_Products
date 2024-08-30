package com.sampathproducts.CustomerOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.CustomerOrderHasProduct.CustomerOrderHasProduct;
import com.sampathproducts.Email.EmailDetails;
import com.sampathproducts.Email.EmailService;
import com.sampathproducts.Material.Material;
import com.sampathproducts.Material.MaterialDao;
import com.sampathproducts.ModuleRole.ModuleRoleController;
import com.sampathproducts.Product.Product;
import com.sampathproducts.Product.ProductDao;
import com.sampathproducts.ProductHasMaterial.ProductHasMaterial;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderDao dao;

    @Autowired
    private ProductDao daoProduct;

    @Autowired
    private MaterialDao daoMaterial;

    @Autowired
    private EmailService emailServiceImpl;

    @Autowired
    private ModuleRoleController moduleRoleController;

    // create mapping ui
    @RequestMapping(value = "/customerorder")
    public ModelAndView customerorderUI() {

        ModelAndView viewOrder = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewOrder.addObject("logusername", auth.getName());
        viewOrder.setViewName("CustomerOrder/CustOrder.html");
        return viewOrder;
    }

    @RequestMapping(value = "/customerorderready")
    public ModelAndView customerordervalidUI() {
        ModelAndView viewOrder = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewOrder.addObject("logusername", auth.getName());
        viewOrder.setViewName("CustomerOrder/CustOrderReady.html");
        return viewOrder;
    }

    @RequestMapping(value = "/customerordershipped")
    public ModelAndView customerorderExpiredUI() {
        ModelAndView viewOrder = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewOrder.addObject("logusername", auth.getName());
        viewOrder.setViewName("CustomerOrder/CustOrdershipped.html");
        return viewOrder;
    }

    @RequestMapping(value = "/customerorderdelivered")
    public ModelAndView customerorderEndingUI() {
        ModelAndView viewOrder = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewOrder.addObject("logusername", auth.getName());
        viewOrder.setViewName("CustomerOrder/CustOrderDelivered.html");
        return viewOrder;
    }

    @RequestMapping(value = "/customerorderadd")
    public ModelAndView customerorderAddUI() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView viewOrderAdd = new ModelAndView();
        viewOrderAdd.addObject("logusername", auth.getName());
        viewOrderAdd.setViewName("Order/OrderAdd.html");
        return viewOrderAdd;
    }

    // get database values as json data
    @GetMapping(value = "/customerorders/findall", produces = "application/json")
    public List<CustomerOrder> findAll() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Customer_Order");

        if (!logUserPrivi.get("select")) {
            return null;
        }

        return dao.findAll();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/customerorder/findall/created", produces = "application/json")
    public List<CustomerOrder> findAllExist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Customer_Order");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getCreatedCustomerOrder();
    }

    @GetMapping(value = "/customerorder/findall/ready", produces = "application/json")
    public List<CustomerOrder> readyOrders() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Customer_Order");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getReadyCustomerOrder();
    }

    @GetMapping(value = "/customerorder/findall/shipped", produces = "application/json")
    public List<CustomerOrder> shippedOrders() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Customer_Order");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getShippedCustomerOrder();
    }

    @GetMapping(value = "/customerorder/findall/delivered", produces = "application/json")
    public List<CustomerOrder> deliveredOrders() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Customer_Order");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getDeliveredCustomerOrder();
    }

    // Save a Order with post method
    @PostMapping(value = "/customerorder/save")
    public String save(@RequestBody CustomerOrder customerorder) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Customer_Order");

        if (!logUserPrivi.get("insert")) {
            return "Not Completed. You have no privilages.";
        }

        try {
            String nextOrderCode = dao.getNextCustomerOrderCode();

            if (nextOrderCode == "" || nextOrderCode == null)
                customerorder.setCustomer_order_code("cord/001");
            else
                customerorder.setCustomer_order_code(nextOrderCode);

            for (CustomerOrderHasProduct ohp : customerorder.getCustomerOrderHasProductList()) {
                ohp.setCustomer_order_id(customerorder);
            }

            CustomerOrder savCustomerOrder = dao.save(customerorder);

            String customerName = savCustomerOrder.getCustomer_id().getCustomer_name().replaceAll("_", " ");
            LocalDateTime date = savCustomerOrder.getCustomer_order_created();

            String message = "Dear " + customerName + ", \n\n\tWe have recieved your order on " + date.getYear() + "-"
                    + date.getMonth() + "-" + date.getDayOfMonth() + ".\n\nOrder Details: \n\n\tTotal = Rs."
                    + savCustomerOrder.getPayment_amount() + "\n\tDiscount = Rs."
                    + savCustomerOrder.getPayment_discount() + "\n\tPayment Paid = Rs."
                    + savCustomerOrder.getPayment_paid() + "\n\tPayment Method = Rs."
                    + savCustomerOrder.getPayment_method() + "\n\nOrdered products :\n";

            for (CustomerOrderHasProduct ohp : savCustomerOrder.getCustomerOrderHasProductList()) {
                Product orderProduct = daoProduct.getReferenceById(ohp.getProduct_id().getProduct_id());
                message += orderProduct.getProducttype_id().getProducttype_name().replaceAll("_", " ") + " - "
                        + orderProduct.getProductsize_id().getProductsize_name().replace("_", " ") + "\t\t"
                        + ohp.getQuantity() + "Packets. \t\tRs. " + ohp.getPrice();
                for (ProductHasMaterial phm : orderProduct.getProduct_has_material_list()) {
                    Material orderMaterial = daoMaterial.getReferenceById(phm.getMaterial_id().getMaterial_id());
                    orderMaterial.setMaterial_want(
                            orderMaterial.getMaterial_want() + ohp.getQuantity() * phm.getQuantity_needed());
                }
                orderProduct.setProduct_need(orderProduct.getProduct_need() + ohp.getQuantity());
                daoProduct.save(orderProduct);

            }
            message += "\n\n Thank you! \n\nBest Regards, \n Sampath Products.";
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setSendTo(savCustomerOrder.getCustomer_id().getCustomer_email());
            emailDetails.setMsgBody(message);
            emailDetails.setSubject("Customer Order Creation " + savCustomerOrder.getCustomer_order_code());

            emailServiceImpl.sendSimpleMail(emailDetails);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/customerorder/edit")
    public String edit(@RequestBody CustomerOrder customerorder) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Customer_Order");

        if (!logUserPrivi.get("update")) {
            return "Not Completed. You Have no privilages.";
        }

        try {
            String message = "Dear " + customerorder.getCustomer_id().getCustomer_name()
                    + ", \n\n\t Your customer order with code " + customerorder.getCustomer_order_code();

            CustomerOrder customerOrder = dao.getReferenceById(customerorder.getCustomer_order_id());
            customerOrder = customerorder;

            for (CustomerOrderHasProduct ohp : customerOrder.getCustomerOrderHasProductList()) {
                ohp.setCustomer_order_id(customerOrder);
            }

            String ordStatus = customerOrder.getCustomer_order_status();

            switch (ordStatus) {
                case "progress":
                    for (CustomerOrderHasProduct ohp : customerOrder.getCustomerOrderHasProductList()) {
                        Product product = ohp.getProduct_id();
                        product.setProduct_has(product.getProduct_has() - ohp.getQuantity());
                        product.setProduct_has(product.getProduct_need() - ohp.getQuantity());
                        daoProduct.save(product);
                    }
                    message += " is beign processing.";
                    break;

                case "shipped":
                    message += " has been shipped.";
                    break;

                case "delivered":
                    message += " has been delivered. ";
                    break;

                default:
                    break;
            }

            dao.save(customerOrder);

            message += "\n\n Thank you! \n\nBest Regards, \n Sampath Products.";
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setSendTo(customerorder.getCustomer_id().getCustomer_email());
            emailDetails.setMsgBody(message);
            emailDetails.setSubject("Customer Order Updating " + customerorder.getCustomer_order_code());

            emailServiceImpl.sendSimpleMail(emailDetails);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
