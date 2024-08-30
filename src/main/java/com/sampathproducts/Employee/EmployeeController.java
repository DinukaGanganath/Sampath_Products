package com.sampathproducts.Employee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.ModuleRole.ModuleRoleController;
import com.sampathproducts.User.User;
import com.sampathproducts.User.UserDao;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao dao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public EmployeeController(EmployeeDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/employee")
    public ModelAndView employeeUI() {
        ModelAndView viewEmployee = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewEmployee.addObject("logusername", auth.getName());
        viewEmployee.setViewName("Employee/Employee.html");
        return viewEmployee;
    }

    @RequestMapping(value = "/employeeadd")
    public ModelAndView employeeAddUI() {
        ModelAndView viewEmployeeAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewEmployeeAdd.addObject("logusername", auth.getName());
        viewEmployeeAdd.setViewName("Employee/EmployeeAdd.html");
        return viewEmployeeAdd;
    }

    @RequestMapping(value = "/employeedeleted")
    public ModelAndView employeeDeletedItems() {
        ModelAndView viewEmployeeAdd = new ModelAndView();
        viewEmployeeAdd.setViewName("Employee/EmployeeDeleted.html");
        return viewEmployeeAdd;
    }

    @RequestMapping(value = "/employeeedit")
    public ModelAndView employeeEditUI() {
        ModelAndView viewEmployeeEdit = new ModelAndView();
        viewEmployeeEdit.setViewName("Employee/EmployeeEdit.html");
        return viewEmployeeEdit;
    }

    @RequestMapping(value = "/employeeview")
    public ModelAndView employeeViewUI() {
        ModelAndView viewEmployeeView = new ModelAndView();
        viewEmployeeView.setViewName("Employee/EmployeeView.html");
        return viewEmployeeView;
    }

    // get database values as json data
    @GetMapping(value = "/employee/findall", produces = "application/json")
    public List<Employee> findAll() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.findAll(Sort.by(Direction.DESC, "employeeid"));
    }

    // get database values as json data
    @GetMapping(value = "/employee/findall/deleted", produces = "application/json")
    public List<Employee> findAllDeleted() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getDeletedEmployee();
    }

    // get database values as json data
    @GetMapping(value = "/employee/findall/nouser", produces = "application/json")
    public List<Employee> findAllNoUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getEmployeeNoUser();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/employee/findall/exist", produces = "application/json")
    public List<Employee> findAllExist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getExistingEmployee();
    }

    // Save a Employee with post method
    @PostMapping(value = "/employee/save")
    public String save(@RequestBody Employee employee) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("insert")) {
            return "Not Completed. No Privilages";
        }
        try {
            employee.setCreated_date_time(LocalDateTime.now());
            employee.setEmployee_deleted(0);

            String nextEmployeeCode = dao.getNextEmployeeCode();

            if (nextEmployeeCode == "" || nextEmployeeCode == null) {
                employee.setEmployee_code("emp/001");
            } else {
                employee.setEmployee_code(nextEmployeeCode);
            }

            dao.save(employee);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @Transactional
    @DeleteMapping(value = "/employee/delete")
    public String delete(@RequestBody Employee employee) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("delete")) {
            return "Not Completed. No Privilages";
        }
        try {
            @SuppressWarnings("null")
            Employee extEmployee = dao.getReferenceById(employee.getEmployee_id());
            extEmployee.setEmployee_deleted(1);
            extEmployee.setDeleted_date_time(LocalDateTime.now());
            dao.save(extEmployee);

            User extUser = userDao.getUserByEmployee(extEmployee.getEmployee_id());
            if (extUser != null) {
                extUser.setUser_status(false);
                userDao.save(extUser);
            }
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @Transactional
    @PutMapping(value = "/employee/restore")
    public String restore(@RequestBody Employee employee) {
        System.out.println(employee.getEmployee_id());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("update")) {
            return "Not Completed. No Privilages";
        }
        try {
            @SuppressWarnings("null")
            Employee extEmployee = dao.getReferenceById(employee.getEmployee_id());
            extEmployee.setEmployee_deleted(0);
            dao.save(extEmployee);

            User extUser = userDao.getUserByEmployee(extEmployee.getEmployee_id());
            if (extUser != null) {
                extUser.setUser_status(true);
                userDao.save(extUser);
            }

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/employee/edit")
    public String edit(@RequestBody Employee employee) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("update")) {
            return "Not Completed. No Privilages";
        }
        try {

            @SuppressWarnings("null")
            Employee extEmployee = dao.getReferenceById(employee.getEmployee_id());
            extEmployee = employee;
            employee.setEmployee_deleted(0);
            extEmployee.setUpdated_date_time(LocalDateTime.now());
            dao.save(extEmployee);

            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }

    // get the last added employee
    @GetMapping("/lastemployee")
    public List<Employee> latestEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Employee");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getLatestEmployee();
    }

}
