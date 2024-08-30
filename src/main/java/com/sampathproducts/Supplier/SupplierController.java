package com.sampathproducts.Supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.ModuleRole.ModuleRoleController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SupplierController {

    @Autowired
    private SupplierDao dao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public SupplierController(SupplierDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/supplier")
    public ModelAndView supplierUI() {
        ModelAndView viewSupplier = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewSupplier.addObject("logusername", auth.getName());
        viewSupplier.setViewName("Supplier/Supplier.html");
        return viewSupplier;
    }

    @RequestMapping(value = "/supplieradd")
    public ModelAndView supplierAddUI() {
        ModelAndView viewSupplierAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewSupplierAdd.addObject("logusername", auth.getName());
        viewSupplierAdd.setViewName("Supplier/SupplierAdd.html");
        return viewSupplierAdd;
    }

    @RequestMapping(value = "/supplierdeleted")
    public ModelAndView supplierDeletedItems() {
        ModelAndView viewSupplierAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewSupplierAdd.addObject("logusername", auth.getName());
        viewSupplierAdd.setViewName("Supplier/SupplierDeleted.html");
        return viewSupplierAdd;
    }

    @RequestMapping(value = "/supplieredit")
    public ModelAndView supplierEditUI() {
        ModelAndView viewSupplierEdit = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewSupplierEdit.addObject("logusername", auth.getName());
        viewSupplierEdit.setViewName("Supplier/SupplierEdit.html");
        return viewSupplierEdit;
    }

    @RequestMapping(value = "/supplierview")
    public ModelAndView supplierViewUI() {
        ModelAndView viewSupplierView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewSupplierView.addObject("logusername", auth.getName());
        viewSupplierView.setViewName("Supplier/SupplierView.html");
        return viewSupplierView;
    }

    // get database values as json data
    @GetMapping(value = "/supplier/findall", produces = "application/json")
    public List<Supplier> findAll() {
        return dao.findAll(Sort.by(Direction.DESC, "supplierid"));
    }

    // get database values as json data
    @GetMapping(value = "/supplier/findall/deleted", produces = "application/json")
    public List<Supplier> findAllDeleted() {
        return dao.getDeletedSupplier();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/supplier/findall/exist", produces = "application/json")
    public List<Supplier> findAllExist() {
        return dao.getExistingSupplier();
    }

    // get database quotation makeable values as json data
    @GetMapping(value = "/supplier/findall/quotation", produces = "application/json")
    public List<Supplier> findAllQuotationSupplier() {
        return dao.getQuotationSupplier();
    }

    // Save a Supplier with post method
    @PostMapping(value = "/supplier/save")
    public String save(@RequestBody Supplier supplier) {

        try {
            supplier.setCreated_date_time(LocalDateTime.now());
            supplier.setSupplier_deleted(0);

            String nextSupplierCode = dao.getNextSupplierCode();

            if (nextSupplierCode == "" || nextSupplierCode == null) {
                supplier.setSupplier_code("sup/001");
            } else {
                supplier.setSupplier_code(nextSupplierCode);
            }

            dao.save(supplier);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/supplier/delete")
    public String delete(@RequestBody Supplier supplier) {
        try {
            @SuppressWarnings("null")
            Supplier extSupplier = dao.getReferenceById(supplier.getSupplierid());
            extSupplier.setSupplier_deleted(1);
            extSupplier.setDeleted_date_time(LocalDateTime.now());
            dao.save(extSupplier);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/supplier/restore")
    public String restore(@RequestBody Supplier supplier) {
        System.out.println(supplier.getSupplierid());
        try {
            @SuppressWarnings("null")
            Supplier extSupplier = dao.getReferenceById(supplier.getSupplierid());
            extSupplier.setSupplier_deleted(0);
            dao.save(extSupplier);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/supplier/edit")
    public String edit(@RequestBody Supplier supplier) {

        try {

            @SuppressWarnings("null")
            Supplier extSupplier = dao.getReferenceById(supplier.getSupplierid());
            extSupplier = supplier;
            supplier.setSupplier_deleted(0);
            extSupplier.setUpdated_date_time(LocalDateTime.now());
            dao.save(extSupplier);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
