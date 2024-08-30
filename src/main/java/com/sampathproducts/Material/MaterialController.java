package com.sampathproducts.Material;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MaterialController {

    @Autowired
    private MaterialDao dao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public MaterialController(MaterialDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/material")
    public ModelAndView materialUI() {
        ModelAndView viewMaterial = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewMaterial.addObject("logusername", auth.getName());
        viewMaterial.setViewName("Material/Material.html");
        return viewMaterial;
    }

    @RequestMapping(value = "/materialadd")
    public ModelAndView materialAddUI() {
        ModelAndView viewMaterialAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewMaterialAdd.addObject("logusername", auth.getName());
        viewMaterialAdd.setViewName("Material/MaterialAdd.html");
        return viewMaterialAdd;
    }

    @RequestMapping(value = "/materialneed")
    public ModelAndView materialNeedUI() {
        ModelAndView viewMaterialAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewMaterialAdd.addObject("logusername", auth.getName());
        viewMaterialAdd.setViewName("Material/MaterialNeed.html");
        return viewMaterialAdd;
    }

    @RequestMapping(value = "/materialordered")
    public ModelAndView materialOrderUI() {
        ModelAndView viewMaterialAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewMaterialAdd.addObject("logusername", auth.getName());
        viewMaterialAdd.setViewName("Material/MaterialOrdered.html");
        return viewMaterialAdd;
    }

    @RequestMapping(value = "/materialreceived")
    public ModelAndView materialRecievedUI() {
        ModelAndView viewMaterialAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewMaterialAdd.addObject("logusername", auth.getName());
        viewMaterialAdd.setViewName("Material/MaterialRecieved.html");
        return viewMaterialAdd;
    }

    // get database values as json data
    @GetMapping(value = "/materials/findall", produces = "application/json")
    public List<Material> findAll() {
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/material/findall/deleted", produces = "application/json")
    public List<Material> findAllDeleted() {
        return dao.getDeletedMaterial();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/material/findall/exist", produces = "application/json")
    public List<Material> findAllExist() {
        return dao.getExistingMaterial();
    }

    // Save a Material with post method
    @PostMapping(value = "/material/save")
    public String save(@RequestBody Material material) {

        try {
            material.setMaterial_added_date(LocalDateTime.now());
            material.setMaterial_has(0);
            material.setMaterial_want(0);

            String nextMaterialCode = dao.getNextMaterialCode();

            if (nextMaterialCode == "" || nextMaterialCode == null)
                material.setMaterial_code("mat/001");
            else
                material.setMaterial_code(nextMaterialCode);

            material.setMaterial_deleted(0);
            dao.save(material);
            System.out.println(material);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/material/delete")
    public String delete(@RequestBody Material material) {
        try {
            @SuppressWarnings("null")
            Material extSupplier = dao.getReferenceById(material.getMaterial_id());
            extSupplier.setMaterial_deleted_date(LocalDateTime.now());
            extSupplier.setMaterial_deleted(1);
            dao.save(extSupplier);

            return "Ok";
        } catch (Exception e) {
            return "Delete not completed : " + e.getMessage();
        }
    }

    @RequestMapping(value = "/materialdeleted")
    public ModelAndView materialDeletedItems() {
        ModelAndView viewSupplierAdd = new ModelAndView();
        viewSupplierAdd.setViewName("Material/MaterialDeleted.html");
        return viewSupplierAdd;
    }

    @PutMapping(value = "/material/restore")
    public String restore(@RequestBody Material supplier) {
        System.out.println(supplier.getMaterial_id());
        try {
            @SuppressWarnings("null")
            Material extSupplier = dao.getReferenceById(supplier.getMaterial_id());
            extSupplier.setMaterial_deleted(0);
            dao.save(extSupplier);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @SuppressWarnings("null")
    @PutMapping("/material/edit")
    public String updateArea(@RequestBody Material material) {

        try {
            Material extArea = dao.getReferenceById(material.getMaterial_id());
            extArea = material;
            extArea.setMaterial_updated_date(LocalDateTime.now());
            dao.save(material);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }

}
