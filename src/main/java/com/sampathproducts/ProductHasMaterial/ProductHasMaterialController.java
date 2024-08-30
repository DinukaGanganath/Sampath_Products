package com.sampathproducts.ProductHasMaterial;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductHasMaterialController {

    private ProductHasMaterialDao dao;

    public ProductHasMaterialController(ProductHasMaterialDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/productmaterial/findall", produces = "application/json")
    public List<ProductHasMaterial> findAll() {
        return dao.findAll();
    }
}
