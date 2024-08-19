package com.sampathproducts.ModuleRole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ModuleRoleController {

    @Autowired
    private ModuleRoleDao dao;

    /*
     * public ModuleRoleController(ModuleRoleDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/privilage")
    public ModelAndView privilageUI() {
        ModelAndView viewModuleRole = new ModelAndView();
        viewModuleRole.setViewName("Privilage/Privilage.html");
        return viewModuleRole;
    }

    @RequestMapping(value = "/privilagedeleted")
    public ModelAndView privilageDeletedUI() {
        ModelAndView viewModuleRoleAdd = new ModelAndView();
        viewModuleRoleAdd.setViewName("ModuleRole/ModuleRoleDeleted.html");
        return viewModuleRoleAdd;
    }

    @RequestMapping(value = "/privilageAdd")
    public ModelAndView privilageAddUI() {
        ModelAndView viewModuleRoleAdd = new ModelAndView();
        viewModuleRoleAdd.setViewName("ModuleRole/ModuleRoleAdd.html");
        return viewModuleRoleAdd;
    }

    // get database values as json data
    @GetMapping(value = "/privilage/findall", produces = "application/json")
    public List<ModuleRole> findAll() {

        return dao.findAll();
    }

    @PutMapping(value = "/modulerole/edit")
    public String restore(@RequestBody ModuleRole privilage) {
        try {

            System.out.println(privilage);
            // dao.save(privilage);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

}
