package com.sampathproducts.Role;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sampathproducts.ModuleRole.ModuleRoleController;

@RestController
public class RoleController {
    private RoleDao dao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    public RoleController(RoleDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/role/findall", produces = "application/json")
    public List<Role> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "Role");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.findAll();
    }

    @PostMapping(value = "/role/save")
    public String save(@RequestBody Role role) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "Role");

        if (!logUserPrivi.get("insert")) {
            return "Not Completed. No Privilages";
        }

        try {

            dao.save(role);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }
}
