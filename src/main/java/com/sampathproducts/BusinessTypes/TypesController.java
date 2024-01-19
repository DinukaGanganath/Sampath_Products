package com.sampathproducts.BusinessTypes;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypesController {
    private TypesDao dao;

    public TypesController(TypesDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/types/findall", produces = "application/json")
    public List<Types> findAll() {
        return dao.findAll();
    }
}
