package com.sampathproducts.Supplier;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SupplierController {

    private SupplierDao dao;

    public SupplierController(SupplierDao dao) {
        this.dao = dao;
    }

    // create mapping ui
    @RequestMapping(value = "/supplier")
    public ModelAndView supplierUI() {
        ModelAndView viewSupplier = new ModelAndView();
        viewSupplier.setViewName("Supplier/Supplier.html");
        return viewSupplier;
    }

    @RequestMapping(value = "/supplieradd")
    public ModelAndView materialAddUI() {
        ModelAndView viewMaterialAdd = new ModelAndView();
        viewMaterialAdd.setViewName("Supplier/SupplierAdd.html");
        return viewMaterialAdd;
    }

    // get database values as json data
    @GetMapping(value = "/supplier/findall", produces = "application/json")
    public List<Supplier> findAll() {
        return dao.findAll();
    }

}
