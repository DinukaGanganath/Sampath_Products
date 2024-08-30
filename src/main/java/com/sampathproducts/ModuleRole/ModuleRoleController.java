package com.sampathproducts.ModuleRole;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public ModuleRoleController(ModuleRoleDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/privilage")
    public ModelAndView privilageUI() {
        ModelAndView viewModuleRole = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewModuleRole.addObject("logusername", auth.getName());
        viewModuleRole.setViewName("Privilage/Privilage.html");
        return viewModuleRole;
    }

    @RequestMapping(value = "/privilagedeleted")
    public ModelAndView privilageDeletedUI() {
        ModelAndView viewModuleRoleAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewModuleRoleAdd.addObject("logusername", auth.getName());
        viewModuleRoleAdd.setViewName("ModuleRole/ModuleRoleDeleted.html");
        return viewModuleRoleAdd;
    }

    @RequestMapping(value = "/privilageAdd")
    public ModelAndView privilageAddUI() {
        ModelAndView viewModuleRoleAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewModuleRoleAdd.addObject("logusername", auth.getName());
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

            ModuleRole moduleRole = dao.getModuleRole(privilage.getModule_id().getModule_id(),
                    privilage.getRole_id().getRole_id());

            privilage.setModule_role(moduleRole.getModule_role());

            System.out.println(privilage);
            dao.save(privilage);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    public HashMap<String, Boolean> getPrivilageByUserModule(String username, String modulename) {

        HashMap<String, Boolean> userPrivilage = new HashMap<String, Boolean>();

        if (username.equals("Admin")) {
            userPrivilage.put("select", true);
            userPrivilage.put("insert", true);
            userPrivilage.put("update", true);
            userPrivilage.put("delete", true);
        } else {
            String userPrivi = dao.getPrivilageByUserModule(username, modulename);
            String[] userPriviList = userPrivi.split(",");
            userPrivilage.put("select", userPriviList[0].equals("1"));
            userPrivilage.put("insert", userPriviList[1].equals("1"));
            userPrivilage.put("update", userPriviList[2].equals("1"));
            userPrivilage.put("delete", userPriviList[3].equals("1"));

            System.out.println(userPrivilage.get("select"));
            System.out.println(userPrivilage.get("insert"));
            System.out.println(userPrivilage.get("update"));
            System.out.println(userPrivilage.get("delete"));
        }

        return userPrivilage;

    }

}
