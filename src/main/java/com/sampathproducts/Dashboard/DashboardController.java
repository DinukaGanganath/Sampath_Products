package com.sampathproducts.Dashboard;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class DashboardController {

    @Autowired
    private DashboardDao dao;

    /*
     * public DashboardController(DashboardDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/dashboards/admin")
    public ModelAndView admindashboardUI() {
        ModelAndView viewDashboard = new ModelAndView();
        viewDashboard.setViewName("Dashboard/Admin.html");
        return viewDashboard;
    }

    @RequestMapping(value = "/dashboards/salesrep")
    public ModelAndView salesrepdashboardUI() {
        ModelAndView viewDashboard = new ModelAndView();
        viewDashboard.setViewName("Dashboard/Admin.html");
        return viewDashboard;
    }

    @RequestMapping(value = "/dashboards/manager")
    public ModelAndView managerdashboardUI() {
        ModelAndView viewDashboard = new ModelAndView();
        viewDashboard.setViewName("Dashboard/Admin.html");
        return viewDashboard;
    }

    @RequestMapping(value = "/dashboards/storekeeper")
    public ModelAndView storekeeperdashboardUI() {
        ModelAndView viewDashboard = new ModelAndView();
        viewDashboard.setViewName("Dashboard/Admin.html");
        return viewDashboard;
    }

    @RequestMapping(value = "/dashboarddeleted")
    public ModelAndView dashboardDeletedUI() {
        ModelAndView viewDashboardAdd = new ModelAndView();
        viewDashboardAdd.setViewName("Dashboard/DashboardDeleted.html");
        return viewDashboardAdd;
    }

    @RequestMapping(value = "/dashboardAdd")
    public ModelAndView dashboardAddUI() {
        ModelAndView viewDashboardAdd = new ModelAndView();
        viewDashboardAdd.setViewName("Dashboard/DashboardAdd.html");
        return viewDashboardAdd;
    }

    // get database values as json data
    @GetMapping(value = "/dashboards/findall", produces = "application/json")
    public List<Dashboard> findAll() {
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/dashboards/findall/deleted", produces = "application/json")
    public List<Dashboard> findAllDeleted() {
        return dao.getDeletedDashboard();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/dashboards/findall/exist", produces = "application/json")
    public List<Dashboard> findAllExist() {
        return dao.getExistingDashboard();
    }

    // Save a Material with post method
    @PostMapping(value = "/dashboard/save")
    public String save(@RequestBody Dashboard dashboard) {

        try {
            dashboard.setDashboard_added_date(LocalDateTime.now());

            String nextDashboardCode = dao.getNextDashboardCode();

            if (nextDashboardCode == "" || nextDashboardCode == null) {
                dashboard.setDashboard_code("dashboard/001");
            } else {
                dashboard.setDashboard_code(nextDashboardCode);
            }
            dashboard.setDashboard_added_date(LocalDateTime.now());
            dashboard.setDashboard_deleted(0);

            dao.save(dashboard);
            System.out.println(dashboard);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/dashboard/restore")
    public String restore(@RequestBody Dashboard dashboard) {
        System.out.println(dashboard.getDashboard_id());
        try {
            // @SuppressWarnings("null")
            Dashboard extDashboard = dao.getReferenceById(dashboard.getDashboard_id());
            extDashboard.setDashboard_deleted(0);
            dao.save(extDashboard);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @DeleteMapping(value = "/dashboard/delete")
    public String delete(@RequestBody Dashboard dashboard) {
        try {
            @SuppressWarnings("null")
            Dashboard extDashboard = dao.getReferenceById(dashboard.getDashboard_id());
            extDashboard.setDashboard_deleted(1);
            extDashboard.setDashboard_deleted_date(LocalDateTime.now());
            dao.save(extDashboard);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @SuppressWarnings("null")
    @PutMapping("/dashboard/edit")
    public String updateDashboard(@RequestBody Dashboard dashboard) {

        try {
            Dashboard extDashboard = dao.getReferenceById(dashboard.getDashboard_id());
            extDashboard = dashboard;
            extDashboard.setDashboard_updated_date(LocalDateTime.now());
            dao.save(dashboard);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }

}
