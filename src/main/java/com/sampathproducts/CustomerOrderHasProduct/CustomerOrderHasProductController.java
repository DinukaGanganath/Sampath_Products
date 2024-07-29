package com.sampathproducts.CustomerOrderHasProduct;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerOrderHasProductController {
    private CustomerOrderHasProductDao dao;

    public CustomerOrderHasProductController(CustomerOrderHasProductDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/custorderproduct/findall", produces = "application/json")
    public List<CustomerOrderHasProduct> findAll() {
        return dao.findAll();
    }
}
