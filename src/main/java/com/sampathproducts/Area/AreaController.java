package com.sampathproducts.Area;

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
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class AreaController {

    @Autowired
    private AreaDao dao;

    /*
     * public AreaController(AreaDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/areas")
    public ModelAndView areaUI() {
        ModelAndView viewArea = new ModelAndView();
        viewArea.setViewName("Area/Area.html");
        return viewArea;
    }

    @RequestMapping(value = "/aeraAdd")
    public ModelAndView materialAddUI() {
        ModelAndView viewAreaAdd = new ModelAndView();
        viewAreaAdd.setViewName("Area/AreaAdd.html");
        return viewAreaAdd;
    }

    // get database values as json data
    @GetMapping(value = "/areas/findall", produces = "application/json")
    public List<Area> findAll() {
        return dao.findAll();
    }

    // Save a Material with post method
    @PostMapping(value = "/area/save")
    public String save(@RequestBody Area area) {

        try {
            area.setArea_added_date(LocalDateTime.now());

            String nextAreaCode = dao.getNextAreaCode();

            if (nextAreaCode == "" || nextAreaCode == null) {
                area.setArea_code("area/001");
            } else {
                area.setArea_code(nextAreaCode);
            }

            dao.save(area);
            System.out.println(area);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/area/delete")
    public String delete(@RequestBody Area area) {
        try {
            return "ok";
        } catch (Exception e) {
            return "Delete not completed : " + e.getMessage();
        }
    }

    @PutMapping("/area/update")
    public String updateArea(@RequestBody Area area) {

        try {
            dao.save(area);
            return "ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }

}
