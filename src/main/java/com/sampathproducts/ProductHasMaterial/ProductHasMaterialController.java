package com.sampathproducts.ProductHasMaterial;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sampathproducts.ModuleRole.ModuleRoleController;

@RestController
public class ProductHasMaterialController {

    @Autowired
    private ModuleRoleController moduleRoleController;

    private ProductHasMaterialDao dao;

    public ProductHasMaterialController(ProductHasMaterialDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/productmaterial/findall", produces = "application/json")
    public List<ProductHasMaterial> findAll() {
        return dao.findAll();
    }
}
