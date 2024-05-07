package com.sampathproducts.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    // creating the code
    @Query(value = "select concat('emp/',lpad((substring(max(employee_code),(position('/'in max(employee_code)))+1, 3)+1),3,0)) FROM sampathproducts.employee_details;", nativeQuery = true)
    public String getNextEmployeeCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.employee_details where employee_deleted=1 order by employee_id desc", nativeQuery = true)
    public List<Employee> getDeletedEmployee();

    // get non deleted values
    @Query(value = "select * from sampathproducts.employee_details where employee_deleted=0 order by employee_id desc", nativeQuery = true)
    public List<Employee> getExistingEmployee();

    // get Last Added Employee
    @Query(value = "select * from sampathproducts.employee_details where employee_id=(select max(employee_id) from sampathproducts.employee_details);", nativeQuery = true)
    public List<Employee> getLatestEmployee();

}
