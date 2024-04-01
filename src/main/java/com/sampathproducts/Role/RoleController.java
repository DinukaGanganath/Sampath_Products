package com.sampathproducts.Role;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private RoleDao dao;

    public RoleController(RoleDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/role/findall", produces = "application/json")
    public List<Role> findAll() {
        return dao.findAll();
    }
}
