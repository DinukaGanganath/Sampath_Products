package com.sampathproducts.Module;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sampathproducts.ModuleRole.ModuleRole;
import com.sampathproducts.ModuleRole.ModuleRoleController;
import com.sampathproducts.ModuleRole.ModuleRoleDao;
import com.sampathproducts.Role.Role;
import com.sampathproducts.Role.RoleDao;

@RestController
public class ModuleController {
    private ModuleDao dao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ModuleRoleDao moduleRoleDao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    public ModuleController(ModuleDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/module/findall", produces = "application/json")
    public List<Module> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "Module");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.findAll();
    }

    @PostMapping(value = "/module/save")
    public String save(@RequestBody Module module) {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                    "Module");

            if (!logUserPrivi.get("insert")) {
                return "Not Completed. No Privilages";
            }

            dao.save(module);

            for (Role role : roleDao.findAll()) {
                ModuleRole moduleRole = new ModuleRole();
                moduleRole.setCre(0);
                moduleRole.setDel(0);
                moduleRole.setModule_id(dao.getLatestModule());
                moduleRole.setRole_id(role);
                moduleRole.setSel(0);
                moduleRole.setUpd(0);

                moduleRoleDao.save(moduleRole);
            }

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/module/edit")
    public String restore(@RequestBody Module module) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                    "Module");

            if (!logUserPrivi.get("update")) {
                return "Not Completed. No Privilages";
            }

            dao.save(module);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }
}
