package com.sampathproducts.BatchHasProduct;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchHasProductController {
    private BatchHasProductDao dao;

    public BatchHasProductController(BatchHasProductDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/batchproduct/findall", produces = "application/json")
    public List<BatchHasProduct> findAll() {
        return dao.findAll();
    }
}
