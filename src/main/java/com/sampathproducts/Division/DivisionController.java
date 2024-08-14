package com.sampathproducts.Division;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionController {

    @Autowired
    private DivisionDao dao;

    /*
     * public MaterialController(MaterialDao dao) {
     * this.dao = dao;
     * }
     */

    // get database values as json data
    @GetMapping(value = "/division/findall", produces = "application/json")
    public List<Division> findAll() {
        return dao.findAll();
    }

    @PostMapping(value = "/division/save")
    public String save(@RequestBody Division division) {

        try {
            dao.save(division);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }
}
