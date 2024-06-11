package com.sampathproducts.Quotation;

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
public class QuotationController {

    @Autowired
    private QuotationDao dao;

    /*
     * public QuotationController(QuotationDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/quotation")
    public ModelAndView quotationAllUI() {
        ModelAndView viewQuotation = new ModelAndView();
        viewQuotation.setViewName("Quotation/Quotation.html");
        return viewQuotation;
    }

    // create mapping ui
    @RequestMapping(value = "/requestedquot")
    public ModelAndView quotationRequestedUI() {
        ModelAndView viewQuotation = new ModelAndView();
        viewQuotation.setViewName("Quotation/QuotationRequested.html");
        return viewQuotation;
    }

    // create mapping ui
    @RequestMapping(value = "/validquot")
    public ModelAndView quotationValidUI() {
        ModelAndView viewQuotation = new ModelAndView();
        viewQuotation.setViewName("Quotation/QuotationValid.html");
        return viewQuotation;
    }

    // create mapping ui
    @RequestMapping(value = "/endingquot")
    public ModelAndView quotationEndingUI() {
        ModelAndView viewQuotation = new ModelAndView();
        viewQuotation.setViewName("Quotation/QuotationEnding.html");
        return viewQuotation;
    }

    // create mapping ui
    @RequestMapping(value = "/expirededquot")
    public ModelAndView quotationExpiredUI() {
        ModelAndView viewQuotation = new ModelAndView();
        viewQuotation.setViewName("Quotation/QuotationExpired.html");
        return viewQuotation;
    }

    @RequestMapping(value = "/quotationadd")
    public ModelAndView quotationAddUI() {
        ModelAndView viewQuotationAdd = new ModelAndView();
        viewQuotationAdd.setViewName("Quotation/QuotationAdd.html");
        return viewQuotationAdd;
    }

    @RequestMapping(value = "/quotationedit")
    public ModelAndView quotationEditUI() {
        ModelAndView viewQuotationEdit = new ModelAndView();
        viewQuotationEdit.setViewName("Quotation/QuotationEdit.html");
        return viewQuotationEdit;
    }

    @RequestMapping(value = "/quotationview")
    public ModelAndView quotationViewUI() {
        ModelAndView viewQuotationView = new ModelAndView();
        viewQuotationView.setViewName("Quotation/QuotationView.html");
        return viewQuotationView;
    }

    // get database values as json data
    @GetMapping(value = "/quotation/findall", produces = "application/json")
    public List<Quotation> findAll() {
        return dao.findAll(Sort.by(Direction.DESC, "quotationid"));
    }

    // get database exsisting values as json data
    @GetMapping(value = "/quotation/findall/submitted", produces = "application/json")
    public List<Quotation> findAllExist() {
        return dao.getAllQuotation();
    }

    // get database values as json data
    @GetMapping(value = "/quotation/findall/requested", produces = "application/json")
    public List<Quotation> findAllRequested() {
        return dao.getRequestedQuotation();
    }

    // get database values as json data
    @GetMapping(value = "/quotation/findall/valid", produces = "application/json")
    public List<Quotation> findAllValid() {
        return dao.getValidQuotation();
    }

    // get database values as json data
    @GetMapping(value = "/quotation/findall/ending", produces = "application/json")
    public List<Quotation> findAllEnding() {
        return dao.getEndingQuotation();
    }

    // get database values as json data
    @GetMapping(value = "/quotation/findall/expired", produces = "application/json")
    public List<Quotation> findAllExpired() {
        return dao.getExpiredQuotation();
    }

    // Save a Quotation with post method
    @PostMapping(value = "/quotation/save")
    public String save(@RequestBody Quotation quotation) {

        try {
            quotation.setQuotation_created_date(LocalDateTime.now());

            String nextQuotationCode = dao.getNextQuotationCode();

            if (nextQuotationCode == "" || nextQuotationCode == null) {
                quotation.setQuotation_code("quot/001");
            } else {
                quotation.setQuotation_code(nextQuotationCode);
            }

            dao.save(quotation);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/quotation/delete")
    public String delete(@RequestBody Quotation quotation) {
        try {
            @SuppressWarnings("null")
            Quotation extQuotation = dao.getReferenceById(quotation.getQuotationid());
            dao.save(extQuotation);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/quotation/restore")
    public String restore(@RequestBody Quotation quotation) {
        System.out.println(quotation.getQuotationid());
        try {
            @SuppressWarnings("null")
            Quotation extQuotation = dao.getReferenceById(quotation.getQuotationid());
            dao.save(extQuotation);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/quotation/edit")
    public String edit(@RequestBody Quotation quotation) {

        try {

            @SuppressWarnings("null")
            Quotation extQuotation = dao.getReferenceById(quotation.getQuotationid());
            extQuotation = quotation;
            dao.save(extQuotation);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
