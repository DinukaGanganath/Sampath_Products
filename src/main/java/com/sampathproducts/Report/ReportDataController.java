package com.sampathproducts.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sampathproducts.Batch.Batch;
import com.sampathproducts.Request.Request;

import java.util.List;

@RestController
public class ReportDataController {

    @Autowired
    private ReportDao daoReport;

    @GetMapping(value = "/reportdatabatchdetails", produces = "application/json")
    public String[][] getBatchList() {
        return daoReport.batchDetailsList();
    }

    // @GetMapping(value = "/reportquotationlist", params = { "supplier" }, produces
    // = "application/json")
    // public List<Request> getQuotationList(@RequestParam("supplier") int supplier)
    // {
    // return daoReport.requestListBySupplier(supplier);
    // }

}
