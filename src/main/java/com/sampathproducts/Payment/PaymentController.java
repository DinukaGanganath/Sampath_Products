package com.sampathproducts.Payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PaymentController {

    @Autowired
    private PaymentDao dao;

    /*
     * public PaymentController(PaymentDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/payments")
    public ModelAndView paymentUI() {
        ModelAndView viewPayment = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPayment.addObject("logusername", auth.getName());
        viewPayment.setViewName("Payment/Payment.html");
        return viewPayment;
    }

    @RequestMapping(value = "/paymentdeleted")
    public ModelAndView paymentDeletedUI() {
        ModelAndView viewPaymentAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPaymentAdd.addObject("logusername", auth.getName());
        viewPaymentAdd.setViewName("Payment/PaymentDeleted.html");
        return viewPaymentAdd;
    }

    @RequestMapping(value = "/paymentAdd")
    public ModelAndView paymentAddUI() {
        ModelAndView viewPaymentAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPaymentAdd.addObject("logusername", auth.getName());
        viewPaymentAdd.setViewName("Payment/PaymentAdd.html");
        return viewPaymentAdd;
    }

    // get database values as json data
    @GetMapping(value = "/payments/findall", produces = "application/json")
    public List<Payment> findAll() {
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/payments/findall/deleted", produces = "application/json")
    public List<Payment> findAllDeleted() {
        return dao.getDeletedPayment();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/payments/findall/exist", produces = "application/json")
    public List<Payment> findAllExist() {
        return dao.getExistingPayment();
    }

    // Save a Material with post method
    @PostMapping(value = "/payment/save")
    public String save(@RequestBody Payment payment) {

        try {
            dao.save(payment);
            System.out.println(payment);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/payment/restore")
    public String restore(@RequestBody Payment payment) {
        System.out.println(payment.getPayment_id());
        try {
            // @SuppressWarnings("null")
            Payment extPayment = dao.getReferenceById(payment.getPayment_id());
            dao.save(extPayment);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @DeleteMapping(value = "/payment/delete")
    public String delete(@RequestBody Payment payment) {
        try {
            @SuppressWarnings("null")
            Payment extPayment = dao.getReferenceById(payment.getPayment_id());
            dao.save(extPayment);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

}
