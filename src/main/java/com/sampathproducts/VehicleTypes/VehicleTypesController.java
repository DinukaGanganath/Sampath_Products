package com.sampathproducts.VehicleTypes;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleTypesController {
    private VehicleTypesDao dao;

    public VehicleTypesController(VehicleTypesDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/vehicletypes/findall", produces = "application/json")
    public List<VehicleTypes> findAll() {
        return dao.findAll();
    }
}
