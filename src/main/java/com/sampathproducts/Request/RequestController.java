package com.sampathproducts.Request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RequestController {

    @Autowired
    private RequestDao dao;

    // create mapping ui
    @RequestMapping(value = "/requestedquot")
    public ModelAndView requestUI() {
        ModelAndView viewRequest = new ModelAndView();
        viewRequest.setViewName("QuotationRequest/QuotationRequest.html");
        return viewRequest;
    }

    @RequestMapping(value = "/requestadd")
    public ModelAndView requestAddUI() {
        ModelAndView viewRequestAdd = new ModelAndView();
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
