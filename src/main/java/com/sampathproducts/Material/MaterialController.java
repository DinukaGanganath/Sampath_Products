package com.sampathproducts.Material;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MaterialController {

    private MaterialDao dao;

    public MaterialController(MaterialDao dao) {
        this.dao = dao;
    }

    // create mapping ui
    @RequestMapping(value = "/materials")
    public ModelAndView materialUI() {
        ModelAndView viewMaterial = new ModelAndView();
        viewMaterial.setViewName("Material.html");
        return viewMaterial;
    }

    // get database values as json data
    @GetMapping(value = "/materials/findall", produces = "application/json")
    public List<Material> findAll() {
        return dao.findAll();
    }

}
