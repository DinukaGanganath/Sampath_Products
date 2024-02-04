package com.sampathproducts.Material;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MaterialController {

    @Autowired
    private MaterialDao dao;

    /*
     * public MaterialController(MaterialDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/materials")
    public ModelAndView materialUI() {
        ModelAndView viewMaterial = new ModelAndView();
        viewMaterial.setViewName("Material/Material.html");
        return viewMaterial;
    }

    @RequestMapping(value = "/materialadd")
    public ModelAndView materialAddUI() {
        ModelAndView viewMaterialAdd = new ModelAndView();
        viewMaterialAdd.setViewName("Material/MaterialAdd.html");
        return viewMaterialAdd;
    }

    // get database values as json data
    @GetMapping(value = "/materials/findall", produces = "application/json")
    public List<Material> findAll() {
        return dao.findAll();
    }

    // Save a Material with post method
    @PostMapping(value = "/material/save")
    public String save(@RequestBody Material material) {

        try {
            material.setMaterial_added_date(LocalDateTime.now());

            String nextMaterialCode = dao.getNextMaterialCode();

            if (nextMaterialCode == "" || nextMaterialCode == null) {
                material.setMaterial_code("mat/001");
            } else {
                material.setMaterial_code(nextMaterialCode);
            }

            dao.save(material);
            System.out.println(material);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

}
