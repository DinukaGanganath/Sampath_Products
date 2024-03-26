package com.sampathproducts.Division;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
