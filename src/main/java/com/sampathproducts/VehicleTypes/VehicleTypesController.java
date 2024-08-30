package com.sampathproducts.VehicleTypes;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sampathproducts.ModuleRole.ModuleRoleController;

@RestController
public class VehicleTypesController {

    @Autowired
    private ModuleRoleController moduleRoleController;

    private VehicleTypesDao dao;

    public VehicleTypesController(VehicleTypesDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/vehicletypes/findall", produces = "application/json")
    public List<VehicleTypes> findAll() {
        return dao.findAll();
    }
}
