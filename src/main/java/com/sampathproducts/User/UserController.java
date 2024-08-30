package com.sampathproducts.User;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserController {

    @Autowired
    private UserDao dao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public UserController(UserDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/user")
    public ModelAndView userUI() {
        ModelAndView viewUser = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewUser.addObject("logusername", auth.getName());
        viewUser.setViewName("User/User.html");
        return viewUser;
    }

    @RequestMapping(value = "/useradd")
    public ModelAndView userAddUI() {
        ModelAndView viewUserAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewUserAdd.addObject("logusername", auth.getName());
        viewUserAdd.setViewName("User/UserAdd.html");
        return viewUserAdd;
    }

    @RequestMapping(value = "/userdeleted")
    public ModelAndView userDeletedItems() {
        ModelAndView viewUserAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewUserAdd.addObject("logusername", auth.getName());
        viewUserAdd.setViewName("User/UserDeleted.html");
        return viewUserAdd;
    }

    @RequestMapping(value = "/useredit")
    public ModelAndView userEditUI() {
        ModelAndView viewUserEdit = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewUserEdit.addObject("logusername", auth.getName());
        viewUserEdit.setViewName("User/UserEdit.html");
        return viewUserEdit;
    }

    @RequestMapping(value = "/userview")
    public ModelAndView userViewUI() {
        ModelAndView viewUserView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewUserView.addObject("logusername", auth.getName());
        viewUserView.setViewName("User/UserView.html");
        return viewUserView;
    }

    // get database values as json data
    @GetMapping(value = "/user/findall", produces = "application/json")
    public List<User> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "User");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.findAll(Sort.by(Direction.DESC, "user_id"));
    }

    // get database values as json data
    @GetMapping(value = "/user/findall/deleted", produces = "application/json")
    public List<User> findAllDeleted() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "User");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getDeletedUser();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/user/findall/exist", produces = "application/json")
    public List<User> findAllExist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "User");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getExistingUser();
    }

    // Save a User with post method
    @PostMapping(value = "/user/save")
    public String save(@RequestBody User user) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "User");

        if (!logUserPrivi.get("insert")) {
            return "Not Completed. No Privilages";
        }
        try {
            user.setUser_password(bCryptPasswordEncoder.encode(user.getUser_password()));
            dao.save(user);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/user/delete")
    public String delete(@RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "User");

        if (!logUserPrivi.get("delete")) {
            return "Not Completed. No Privilages";
        }
        try {
            User extUser = dao.getReferenceById(user.getUser_id());

            dao.save(extUser);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/user/restore")
    public String restore(@RequestBody User user) {
        System.out.println(user.getUser_id());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "User");

        if (!logUserPrivi.get("update")) {
            return "Not Completed. No Privilages";
        }
        try {
            User extUser = dao.getReferenceById(user.getUser_id());
            extUser.setUser_status(false);
            dao.save(extUser);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/user/edit")
    public String edit(@RequestBody User user) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(), "User");

        if (!logUserPrivi.get("update")) {
            return "Not Completed. No Privilages";
        }
        try {

            User extUser = dao.getReferenceById(user.getUser_id());
            extUser = user;
            user.setUser_status(false);
            dao.save(extUser);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

}
