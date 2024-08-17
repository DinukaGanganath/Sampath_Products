package com.sampathproducts.Module;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sampathproducts.ModuleRole.ModuleRole;

@RestController
public class ModuleController {
    private ModuleDao dao;

    public ModuleController(ModuleDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/module/findall", produces = "application/json")
    public List<Module> findAll() {
        return dao.findAll();
    }

    @PostMapping(value = "/module/save")
    public String save(@RequestBody Module module) {
        try {

            for (ModuleRole privilage : module.getPrivilages()) {
                privilage.setModule_id(module);
            }
            dao.save(module);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }
}
