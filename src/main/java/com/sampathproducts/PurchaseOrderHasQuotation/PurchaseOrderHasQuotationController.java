package com.sampathproducts.PurchaseOrderHasQuotation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseOrderHasQuotationController {
    private PurchaseOrderHasQuotationDao dao;

    public PurchaseOrderHasQuotationController(PurchaseOrderHasQuotationDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/purchaseorderLine/findall", produces = "application/json")
    public List<PurchaseOrderHasQuotation> findAll() {
        return dao.findAll();
    }
}
