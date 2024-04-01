package com.sampathproducts.Privilage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PrivilageController {

    @Autowired
    private PrivilageDao dao;

    /*
     * public PrivilageController(PrivilageDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/privilage")
    public ModelAndView privilageUI() {
        ModelAndView viewPrivilage = new ModelAndView();
        viewPrivilage.setViewName("Privilage/Privilage.html");
        return viewPrivilage;
    }

    @RequestMapping(value = "/privilagedeleted")
    public ModelAndView privilageDeletedUI() {
        ModelAndView viewPrivilageAdd = new ModelAndView();
        viewPrivilageAdd.setViewName("Privilage/PrivilageDeleted.html");
        return viewPrivilageAdd;
    }

    @RequestMapping(value = "/privilageAdd")
    public ModelAndView privilageAddUI() {
        ModelAndView viewPrivilageAdd = new ModelAndView();
        viewPrivilageAdd.setViewName("Privilage/PrivilageAdd.html");
        return viewPrivilageAdd;
    }

    // get database values as json data
    @GetMapping(value = "/privilage/findall", produces = "application/json")
    public List<Privilage> findAll() {
        return dao.findAll();
    }

    // Save a Material with post method
    @PostMapping(value = "/privilage/save")
    public String save(@RequestBody Privilage privilage) {

        try {
            dao.save(privilage);
            System.out.println(privilage);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @SuppressWarnings("null")
    @PutMapping("/privilage/edit")
    public String updatePrivilage(@RequestBody Privilage privilage) {

        try {
            dao.save(privilage);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }

}
