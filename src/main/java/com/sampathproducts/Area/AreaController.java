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

    @RequestMapping(value = "/areadeleted")
    public ModelAndView areaDeletedUI() {
        ModelAndView viewAreaAdd = new ModelAndView();
        viewAreaAdd.setViewName("Area/AreaDeleted.html");
        return viewAreaAdd;
    }

    @RequestMapping(value = "/aeraAdd")
    public ModelAndView areaAddUI() {
        ModelAndView viewAreaAdd = new ModelAndView();
        viewAreaAdd.setViewName("Area/AreaAdd.html");
        return viewAreaAdd;
    }

    // get database values as json data
    @GetMapping(value = "/areas/findall", produces = "application/json")
    public List<Area> findAll() {
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/areas/findall/deleted", produces = "application/json")
    public List<Area> findAllDeleted() {
        return dao.getDeletedArea();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/areas/findall/exist", produces = "application/json")
    public List<Area> findAllExist() {
        return dao.getExistingArea();
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
            area.setArea_added_date(LocalDateTime.now());
            area.setArea_deleted(0);
            dao.save(area);
            System.out.println(area);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/area/restore")
    public String restore(@RequestBody Area area) {
        System.out.println(area.getArea_id());
        try {
            @SuppressWarnings("null")
            Area extArea = dao.getReferenceById(area.getArea_id());
            extArea.setArea_deleted(0);
            dao.save(extArea);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @DeleteMapping(value = "/area/delete")
    public String delete(@RequestBody Area area) {
        try {
            @SuppressWarnings("null")
            Area extArea = dao.getReferenceById(area.getArea_id());
            extArea.setArea_deleted(1);
            extArea.setArea_deleted_date(LocalDateTime.now());
            dao.save(extArea);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @SuppressWarnings("null")
    @PutMapping("/area/edit")
    public String updateArea(@RequestBody Area area) {

        try {
            Area extArea = dao.getReferenceById(area.getArea_id());
            extArea = area;
            extArea.setArea_updated_date(LocalDateTime.now());
            dao.save(area);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }

}
