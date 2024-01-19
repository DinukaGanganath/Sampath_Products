package com.sampathproducts.Area;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaController {

    private AreaDao dao;

    public AreaController(AreaDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/areas/findall", produces = "application/json")
    public List<Area> findAll() {
        return dao.findAll();
    }

}
