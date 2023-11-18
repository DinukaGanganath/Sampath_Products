package com.sampathproducts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminDashboardController {

    @RequestMapping(value = "/AdminDashboard")
    public ModelAndView adminDashboard() {
        ModelAndView adminDashboardView = new ModelAndView();
        adminDashboardView.setViewName("AdminDashboard.html");
        return adminDashboardView;

    }

}
