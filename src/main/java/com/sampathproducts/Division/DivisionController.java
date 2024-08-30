package com.sampathproducts.Division;

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
public class DivisionController {

    @Autowired
    private DivisionDao dao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public MaterialController(MaterialDao dao) {
     * this.dao = dao;
     * }
     */

    // get database values as json data
    @GetMapping(value = "/division/findall", produces = "application/json")
    public List<Division> findAll() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Division");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.findAll();
    }

    @PostMapping(value = "/division/save")
    public String save(@RequestBody Division division) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Division");

        if (!logUserPrivi.get("insert")) {
            return "Not Completed. You have no privilages.";
        }

        try {
            dao.save(division);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }
}
