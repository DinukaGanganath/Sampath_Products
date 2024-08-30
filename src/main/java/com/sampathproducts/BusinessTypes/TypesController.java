package com.sampathproducts.BusinessTypes;

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
public class TypesController {
    private TypesDao dao;

    @Autowired
    ModuleRoleController moduleRoleController;

    public TypesController(TypesDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/types/findall", produces = "application/json")
    public List<Types> findAll() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "Area");
        if (!logUserPrivi.get("update")) {
            return null;
        }

        return dao.findAll();
    }

    @PostMapping(value = "/types/save")
    public String save(@RequestBody Types type) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Business_Type");

        if (!logUserPrivi.get("insert")) {
            return null;
        }

        try {
            dao.save(type);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }
}
