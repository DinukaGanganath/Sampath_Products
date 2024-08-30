package com.sampathproducts.Vehicle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.ModuleRole.ModuleRoleController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class VehicleController {

    @Autowired
    private VehicleDao dao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public VehicleController(VehicleDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/vehicle")
    public ModelAndView vehicleUI() {
        ModelAndView viewVehicle = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewVehicle.addObject("logusername", auth.getName());
        viewVehicle.setViewName("Vehicle/Vehicle.html");
        return viewVehicle;
    }

    @RequestMapping(value = "/vehicleadd")
    public ModelAndView vehicleAddUI() {
        ModelAndView viewVehicleAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewVehicleAdd.addObject("logusername", auth.getName());
        viewVehicleAdd.setViewName("Vehicle/VehicleAdd.html");
        return viewVehicleAdd;
    }

    @RequestMapping(value = "/vehicledeleted")
    public ModelAndView vehicleDeletedItems() {
        ModelAndView viewVehicleAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewVehicleAdd.addObject("logusername", auth.getName());
        viewVehicleAdd.setViewName("Vehicle/VehicleDeleted.html");
        return viewVehicleAdd;
    }

    @RequestMapping(value = "/vehicleedit")
    public ModelAndView vehicleEditUI() {
        ModelAndView viewVehicleEdit = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewVehicleEdit.addObject("logusername", auth.getName());
        viewVehicleEdit.setViewName("Vehicle/VehicleEdit.html");
        return viewVehicleEdit;
    }

    @RequestMapping(value = "/vehicleview")
    public ModelAndView vehicleViewUI() {
        ModelAndView viewVehicleView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewVehicleView.addObject("logusername", auth.getName());
        viewVehicleView.setViewName("Vehicle/VehicleView.html");
        return viewVehicleView;
    }

    // get database values as json data
    @GetMapping(value = "/vehicle/findall", produces = "application/json")
    public List<Vehicle> findAll() {
        return dao.findAll();
    }

    // get database values as json data
    @GetMapping(value = "/vehicle/findall/deleted", produces = "application/json")
    public List<Vehicle> findAllDeleted() {
        return dao.getDeletedVehicle();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/vehicle/findall/exist", produces = "application/json")
    public List<Vehicle> findAllExist() {
        return dao.getExistingVehicle();
    }

    // Save a Vehicle with post method
    @PostMapping(value = "/vehicle/save")
    public String save(@RequestBody Vehicle vehicle) {

        try {
            vehicle.setCreated_date_time(LocalDateTime.now());
            vehicle.setVehicle_deleted(0);

            String nextVehicleCode = dao.getNextVehicleCode();

            if (nextVehicleCode == "" || nextVehicleCode == null) {
                vehicle.setVehicle_code("veh/001");
            } else {
                vehicle.setVehicle_code(nextVehicleCode);
            }

            dao.save(vehicle);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/vehicle/delete")
    public String delete(@RequestBody Vehicle vehicle) {
        try {
            @SuppressWarnings("null")
            Vehicle extVehicle = dao.getReferenceById(vehicle.getVehicle_id());
            extVehicle.setVehicle_deleted(1);
            extVehicle.setDeleted_date_time(LocalDateTime.now());
            dao.save(extVehicle);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/vehicle/restore")
    public String restore(@RequestBody Vehicle vehicle) {
        System.out.println(vehicle.getVehicle_id());
        try {
            @SuppressWarnings("null")
            Vehicle extVehicle = dao.getReferenceById(vehicle.getVehicle_id());
            extVehicle.setVehicle_deleted(0);
            dao.save(extVehicle);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/vehicle/edit")
    public String edit(@RequestBody Vehicle vehicle) {

        try {

            @SuppressWarnings("null")
            Vehicle extVehicle = dao.getReferenceById(vehicle.getVehicle_id());
            extVehicle = vehicle;
            vehicle.setVehicle_deleted(0);
            extVehicle.setUpdated_date_time(LocalDateTime.now());
            dao.save(extVehicle);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
