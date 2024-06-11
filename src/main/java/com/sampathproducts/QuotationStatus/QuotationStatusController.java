package com.sampathproducts.QuotationStatus;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotationStatusController {
    private QuotationStatusDao dao;

    public QuotationStatusController(QuotationStatusDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/quottypes/findall", produces = "application/json")
    public List<QuotationStatus> findAll() {
        return dao.findAll();
    }
}
