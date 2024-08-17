package com.sampathproducts.Role;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(value = "/role/save")
    public String save(@RequestBody Role role) {

        try {
            dao.save(role);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }
}
