package com.sampathproducts.Report;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReportUIController {

    // @Autowired
    // private BatchDao batchDao;

    @RequestMapping(value = "/reportbatchdetails")
    public ModelAndView reportBatchDetails() {
        ModelAndView viewPurchaseOrder = new ModelAndView();
        viewPurchaseOrder.setViewName("Report/BatchDetails.html");
        return viewPurchaseOrder;
    }

}
