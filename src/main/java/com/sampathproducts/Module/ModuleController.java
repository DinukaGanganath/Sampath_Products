package com.sampathproducts.Module;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
