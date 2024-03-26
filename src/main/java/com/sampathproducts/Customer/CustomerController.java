package com.sampathproducts.Customer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CustomerController {

    @Autowired
    private CustomerDao dao;

    /*
     * public CustomerController(CustomerDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/customer")
    public ModelAndView customerUI() {
        ModelAndView viewCustomer = new ModelAndView();
        viewCustomer.setViewName("Customer/Customer.html");
        return viewCustomer;
    }

    @RequestMapping(value = "/customeradd")
    public ModelAndView customerAddUI() {
        ModelAndView viewCustomerAdd = new ModelAndView();
        viewCustomerAdd.setViewName("Customer/CustomerAdd.html");
        return viewCustomerAdd;
    }

    @RequestMapping(value = "/customerdeleted")
    public ModelAndView customerDeletedItems() {
        ModelAndView viewCustomerAdd = new ModelAndView();
        viewCustomerAdd.setViewName("Customer/CustomerDeleted.html");
        return viewCustomerAdd;
    }

    @RequestMapping(value = "/customeredit")
    public ModelAndView customerEditUI() {
        ModelAndView viewCustomerEdit = new ModelAndView();
        viewCustomerEdit.setViewName("Customer/CustomerEdit.html");
        return viewCustomerEdit;
    }

    @RequestMapping(value = "/customerview")
    public ModelAndView customerViewUI() {
        ModelAndView viewCustomerView = new ModelAndView();
        viewCustomerView.setViewName("Customer/CustomerView.html");
        return viewCustomerView;
    }

    // get database values as json data
    @GetMapping(value = "/customer/findall", produces = "application/json")
    public List<Customer> findAll() {
        return dao.findAll(Sort.by(Direction.DESC, "customerid"));
    }

    // get database values as json data
    @GetMapping(value = "/customer/findall/deleted", produces = "application/json")
    public List<Customer> findAllDeleted() {
        return dao.getDeletedCustomer();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/customer/findall/exist", produces = "application/json")
    public List<Customer> findAllExist() {
        return dao.getExistingCustomer();
    }

    // Save a Customer with post method
    @PostMapping(value = "/customer/save")
    public String save(@RequestBody Customer customer) {

        try {
            customer.setCreated_date_time(LocalDateTime.now());
            customer.setCustomer_deleted(0);

            String nextCustomerCode = dao.getNextCustomerCode();

            if (nextCustomerCode == "" || nextCustomerCode == null) {
                customer.setCustomer_code("cust/001");
            } else {
                customer.setCustomer_code(nextCustomerCode);
            }

            dao.save(customer);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/customer/delete")
    public String delete(@RequestBody Customer customer) {
        try {
            @SuppressWarnings("null")
            Customer extCustomer = dao.getReferenceById(customer.getCustomer_id());
            extCustomer.setCustomer_deleted(1);
            extCustomer.setDeleted_date_time(LocalDateTime.now());
            dao.save(extCustomer);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/customer/restore")
    public String restore(@RequestBody Customer customer) {
        System.out.println(customer.getCustomer_id());
        try {
            @SuppressWarnings("null")
            Customer extCustomer = dao.getReferenceById(customer.getCustomer_id());
            extCustomer.setCustomer_deleted(0);
            dao.save(extCustomer);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/customer/edit")
    public String edit(@RequestBody Customer customer) {

        try {

            @SuppressWarnings("null")
            Customer extCustomer = dao.getReferenceById(customer.getCustomer_id());
            extCustomer = customer;
            customer.setCustomer_deleted(0);
            extCustomer.setUpdated_date_time(LocalDateTime.now());
            dao.save(extCustomer);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
