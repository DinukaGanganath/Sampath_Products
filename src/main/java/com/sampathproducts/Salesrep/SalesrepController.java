package com.sampathproducts.Salesrep;

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
public class SalesrepController {

    @Autowired
    private SalesrepDao dao;

    /*
     * public SalesrepController(SalesrepDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/salesrep")
    public ModelAndView salesrepUI() {
        ModelAndView viewSalesrep = new ModelAndView();
        viewSalesrep.setViewName("Salesrep/Salesrep.html");
        return viewSalesrep;
    }

    @RequestMapping(value = "/salesrepadd")
    public ModelAndView salesrepAddUI() {
        ModelAndView viewSalesrepAdd = new ModelAndView();
        viewSalesrepAdd.setViewName("Salesrep/SalesrepAdd.html");
        return viewSalesrepAdd;
    }

    @RequestMapping(value = "/salesrepdeleted")
    public ModelAndView salesrepDeletedItems() {
        ModelAndView viewSalesrepAdd = new ModelAndView();
        viewSalesrepAdd.setViewName("Salesrep/SalesrepDeleted.html");
        return viewSalesrepAdd;
    }

    @RequestMapping(value = "/salesrepedit")
    public ModelAndView salesrepEditUI() {
        ModelAndView viewSalesrepEdit = new ModelAndView();
        viewSalesrepEdit.setViewName("Salesrep/SalesrepEdit.html");
        return viewSalesrepEdit;
    }

    @RequestMapping(value = "/salesrepview")
    public ModelAndView salesrepViewUI() {
        ModelAndView viewSalesrepView = new ModelAndView();
        viewSalesrepView.setViewName("Salesrep/SalesrepView.html");
        return viewSalesrepView;
    }

    // get database values as json data
    @GetMapping(value = "/salesrep/findall", produces = "application/json")
    public List<Salesrep> findAll() {
        return dao.findAll(Sort.by(Direction.DESC, "salesrepid"));
    }

    // get database values as json data
    @GetMapping(value = "/salesrep/findall/deleted", produces = "application/json")
    public List<Salesrep> findAllDeleted() {
        return dao.getDeletedSalesrep();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/salesrep/findall/exist", produces = "application/json")
    public List<Salesrep> findAllExist() {
        return dao.getExistingSalesrep();
    }

    // Save a Salesrep with post method
    @PostMapping(value = "/salesrep/save")
    public String save(@RequestBody Salesrep salesrep) {

        try {
            salesrep.setCreated_date_time(LocalDateTime.now());
            salesrep.setSalesrep_deleted(0);

            String nextSalesrepCode = dao.getNextSalesrepCode();

            if (nextSalesrepCode == "" || nextSalesrepCode == null) {
                salesrep.setSalesrep_code("rep/001");
            } else {
                salesrep.setSalesrep_code(nextSalesrepCode);
            }

            dao.save(salesrep);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/salesrep/delete")
    public String delete(@RequestBody Salesrep salesrep) {
        try {
            @SuppressWarnings("null")
            Salesrep extSalesrep = dao.getReferenceById(salesrep.getSalesrep_id());
            extSalesrep.setSalesrep_deleted(1);
            extSalesrep.setDeleted_date_time(LocalDateTime.now());
            dao.save(extSalesrep);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/salesrep/restore")
    public String restore(@RequestBody Salesrep salesrep) {
        System.out.println(salesrep.getSalesrep_id());
        try {
            @SuppressWarnings("null")
            Salesrep extSalesrep = dao.getReferenceById(salesrep.getSalesrep_id());
            extSalesrep.setSalesrep_deleted(0);
            dao.save(extSalesrep);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/salesrep/edit")
    public String edit(@RequestBody Salesrep salesrep) {

        try {

            @SuppressWarnings("null")
            Salesrep extSalesrep = dao.getReferenceById(salesrep.getSalesrep_id());
            extSalesrep = salesrep;
            salesrep.setSalesrep_deleted(0);
            extSalesrep.setUpdated_date_time(LocalDateTime.now());
            dao.save(extSalesrep);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

    // get the last added salesrep
    @GetMapping("/lastsalesrep")
    public List<Salesrep> latestSalesrep() {
        return dao.getLatestSalesrep();
    }

}
