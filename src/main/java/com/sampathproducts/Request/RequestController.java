package com.sampathproducts.Request;

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
import com.sampathproducts.Supplier.Supplier;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RequestController {

    @Autowired
    private RequestDao dao;

    @Autowired
    private EmailService emailServiceImpl;

    // create mapping ui
    @RequestMapping(value = "/quotation")
    public ModelAndView quotationUI() {
        ModelAndView viewRequest = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewRequest.addObject("logusername", auth.getName());
        viewRequest.setViewName("QuotationRequest/Quotation.html");
        return viewRequest;
    }

    @RequestMapping(value = "/validquot")
    public ModelAndView quotationvalidUI() {
        ModelAndView viewRequest = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewRequest.addObject("logusername", auth.getName());
        viewRequest.setViewName("QuotationRequest/QuotationValid.html");
        return viewRequest;
    }

    @RequestMapping(value = "/expiredquot")
    public ModelAndView quotationExpiredUI() {
        ModelAndView viewRequest = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewRequest.addObject("logusername", auth.getName());
        viewRequest.setViewName("QuotationRequest/QuotationExpired.html");
        return viewRequest;
    }

    @RequestMapping(value = "/endingquot")
    public ModelAndView quotationEndingUI() {
        ModelAndView viewRequest = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewRequest.addObject("logusername", auth.getName());
        viewRequest.setViewName("QuotationRequest/QuotationEnding.html");
        return viewRequest;
    }

    @RequestMapping(value = "/requestedquot")
    public ModelAndView requestUI() {
        ModelAndView viewRequest = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewRequest.addObject("logusername", auth.getName());
        viewRequest.setViewName("QuotationRequest/QuotationRequest.html");
        return viewRequest;
    }

    @RequestMapping(value = "/requestadd")
    public ModelAndView requestAddUI() {
        ModelAndView viewRequestAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewRequestAdd.addObject("logusername", auth.getName());
        viewRequestAdd.setViewName("Request/RequestAdd.html");
        return viewRequestAdd;
    }

    // get database values as json data
    @GetMapping(value = "/requests/findall", produces = "application/json")
    public List<Request> findAll() {
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/request/findall/created", produces = "application/json")
    public List<Request> findAllDeleted() {
        return dao.getCreatedQuotationRequest();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/request/findall/requested", produces = "application/json")
    public List<Request> findAllExist() {
        return dao.getRequestedQuotationRequest();
    }

    @GetMapping(value = "/request/findall/valid", produces = "application/json")
    public List<Request> validRequests() {
        return dao.getvalidQuotations();
    }

    @GetMapping(value = "/request/findall/ending", produces = "application/json")
    public List<Request> endingRequests() {
        return dao.getEndingQuotations();
    }

    @GetMapping(value = "/request/findall/expired", produces = "application/json")
    public List<Request> expiredRequests() {
        return dao.getExpiredQuotations();
    }

    // Save a Request with post method
    @PostMapping(value = "/request/save")
    public String save(@RequestBody Request request) {

        try {
            request.setRequest_date(LocalDateTime.now());

            String nextRequestCode = dao.getNextQuotationRequestCode();

            if (nextRequestCode == "" || nextRequestCode == null)
                request.setRequest_code("req/001");
            else
                request.setRequest_code(nextRequestCode);

            request.setRequest_created(0);
            dao.save(request);
            System.out.println(request);

            // send mail
            Supplier supplier = request.getSupplier_id();
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setSendTo(supplier.getSupplier_email());
            emailDetails.setMsgBody("To " + supplier.getSupplier_business_name().replace('_', ' ')
                    + ", \n\t Since your Quotation period is ended, please send us your new quotations.\nCall or WhatsApp will be also ok. (0707579674) \n Your sincerly, \nSampath Products.");
            emailDetails.setSubject("Requesting new quotation");

            emailServiceImpl.sendSimpleMail(emailDetails);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/request/edit")
    public String edit(@RequestBody Request quotation) {

        try {

            @SuppressWarnings("null")
            Request extQuotation = dao.getReferenceById(quotation.getRequest_id());
            extQuotation = quotation;
            dao.save(extQuotation);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
