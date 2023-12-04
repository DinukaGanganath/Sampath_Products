package com.sampathproducts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FormViewController {

    @RequestMapping(value = "/FormView")
    public ModelAndView adminDashboard() {
        ModelAndView adminDashboardView = new ModelAndView();
        adminDashboardView.setViewName("FormView.html");
        return adminDashboardView;

    }

}
