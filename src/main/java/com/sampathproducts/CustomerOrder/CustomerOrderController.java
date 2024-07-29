package com.sampathproducts.CustomerOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.CustomerOrderHasProduct.CustomerOrderHasProduct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderDao dao;

    // create mapping ui
    @RequestMapping(value = "/customerorder")
    public ModelAndView customerorderUI() {
        ModelAndView viewOrder = new ModelAndView();
        viewOrder.setViewName("CustomerOrder/CustOrder.html");
        return viewOrder;
    }

    @RequestMapping(value = "/validcustomerorder")
    public ModelAndView customerordervalidUI() {
        ModelAndView viewOrder = new ModelAndView();
        viewOrder.setViewName("CustomerOrder/CustOrderValid.html");
        return viewOrder;
    }

    @RequestMapping(value = "/expiredcustomerorder")
    public ModelAndView customerorderExpiredUI() {
        ModelAndView viewOrder = new ModelAndView();
        viewOrder.setViewName("CustomerOrder/CustOrderExpired.html");
        return viewOrder;
    }

    @RequestMapping(value = "/endingcustomerorder")
    public ModelAndView customerorderEndingUI() {
        ModelAndView viewOrder = new ModelAndView();
        viewOrder.setViewName("CustomerOrder/CustOrderEnding.html");
        return viewOrder;
    }

    @RequestMapping(value = "/customerorderadd")
    public ModelAndView customerorderAddUI() {
        ModelAndView viewOrderAdd = new ModelAndView();
        viewOrderAdd.setViewName("Order/OrderAdd.html");
        return viewOrderAdd;
    }

    // get database values as json data
    @GetMapping(value = "/customerorders/findall", produces = "application/json")
    public List<CustomerOrder> findAll() {
        return dao.findAll();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/customerorder/findall/ordered", produces = "application/json")
    public List<CustomerOrder> findAllExist() {
        return dao.getCreatedCustomerOrder();
    }

    @GetMapping(value = "/customerorder/findall/valid", produces = "application/json")
    public List<CustomerOrder> validOrders() {
        return dao.getvalidCustomerOrder();
    }

    @GetMapping(value = "/customerorder/findall/ending", produces = "application/json")
    public List<CustomerOrder> endingOrders() {
        return dao.getEndingCustomerOrder();
    }

    @GetMapping(value = "/customerorder/findall/expired", produces = "application/json")
    public List<CustomerOrder> expiredOrders() {
        return dao.getExpiredCustomerOrder();
    }

    // Save a Order with post method
    @PostMapping(value = "/customerorder/save")
    public String save(@RequestBody CustomerOrder customerorder) {

        try {
            String nextOrderCode = dao.getNextCustomerOrderCode();

            if (nextOrderCode == "" || nextOrderCode == null)
                customerorder.setCustomer_order_code("cord/001");
            else
                customerorder.setCustomer_order_code(nextOrderCode);

            for (CustomerOrderHasProduct ohp : customerorder.getCustomerOrderHasProductList()) {
                ohp.setCustomer_order_id(customerorder);
            }

            // for (ProductHasMaterial phm : product.getProductHasMaterialList()) {
            // phm.setProduct_id(product);
            // }

            dao.save(customerorder);
            System.out.println(customerorder);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/customerorder/edit")
    public String edit(@RequestBody CustomerOrder customerorder) {

        try {

            @SuppressWarnings("null")
            CustomerOrder extQuotation = dao.getReferenceById(customerorder.getCustomer_order_id());
            extQuotation = customerorder;
            dao.save(extQuotation);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
