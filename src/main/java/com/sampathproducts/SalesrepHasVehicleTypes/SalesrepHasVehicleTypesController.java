package com.sampathproducts.SalesrepHasVehicleTypes;

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
public class SalesrepHasVehicleTypesController {

    @Autowired
    private SalesrepHasVehicleTypesDao dao;

    /*
     * public SalesrepHasVehicleTypesController(SalesrepHasVehicleTypesDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/salesrephasvehicletypes")
    public ModelAndView salesrephasvehicletypesUI() {
        ModelAndView viewSalesrepHasVehicleTypes = new ModelAndView();
        viewSalesrepHasVehicleTypes.setViewName("SalesrepHasVehicleTypes/SalesrepHasVehicleTypes.html");
        return viewSalesrepHasVehicleTypes;
    }

    @RequestMapping(value = "/salesrephasvehicletypesadd")
    public ModelAndView salesrephasvehicletypesAddUI() {
        ModelAndView viewSalesrepHasVehicleTypesAdd = new ModelAndView();
        viewSalesrepHasVehicleTypesAdd.setViewName("SalesrepHasVehicleTypes/SalesrepHasVehicleTypesAdd.html");
        return viewSalesrepHasVehicleTypesAdd;
    }

    @RequestMapping(value = "/salesrephasvehicletypesdeleted")
    public ModelAndView salesrephasvehicletypesDeletedItems() {
        ModelAndView viewSalesrepHasVehicleTypesAdd = new ModelAndView();
        viewSalesrepHasVehicleTypesAdd.setViewName("SalesrepHasVehicleTypes/SalesrepHasVehicleTypesDeleted.html");
        return viewSalesrepHasVehicleTypesAdd;
    }

    @RequestMapping(value = "/salesrephasvehicletypesedit")
    public ModelAndView salesrephasvehicletypesEditUI() {
        ModelAndView viewSalesrepHasVehicleTypesEdit = new ModelAndView();
        viewSalesrepHasVehicleTypesEdit.setViewName("SalesrepHasVehicleTypes/SalesrepHasVehicleTypesEdit.html");
        return viewSalesrepHasVehicleTypesEdit;
    }

    @RequestMapping(value = "/salesrephasvehicletypesview")
    public ModelAndView salesrephasvehicletypesViewUI() {
        ModelAndView viewSalesrepHasVehicleTypesView = new ModelAndView();
        viewSalesrepHasVehicleTypesView.setViewName("SalesrepHasVehicleTypes/SalesrepHasVehicleTypesView.html");
        return viewSalesrepHasVehicleTypesView;
    }

    // get database values as json data
    @GetMapping(value = "/salesrephasvehicletypes/findall", produces = "application/json")
    public List<SalesrepHasVehicleTypes> findAll() {
        return dao.findAll(Sort.by(Direction.DESC, "salesrephasvehicletypesid"));
    }

    // get database values as json data
    @GetMapping(value = "/salesrephasvehicletypes/findall/deleted", produces = "application/json")
    public List<SalesrepHasVehicleTypes> findAllDeleted() {
        return dao.getDeletedSalesrepHasVehicleTypes();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/salesrephasvehicletypes/findall/exist", produces = "application/json")
    public List<SalesrepHasVehicleTypes> findAllExist() {
        return dao.getExistingSalesrepHasVehicleTypes();
    }

    // Save a SalesrepHasVehicleTypes with post method
    @PostMapping(value = "/salesrephasvehicletypes/save")
    public String save(@RequestBody SalesrepHasVehicleTypes salesrephasvehicletypes) {

        try {
            dao.save(salesrephasvehicletypes);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PostMapping(value = "/salesrephasvehicletypes/saveall")
    public String saveAllVehicles(@RequestBody List<SalesrepHasVehicleTypes> vehicleTypes) {

        try {
            dao.saveAll(vehicleTypes);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

}
