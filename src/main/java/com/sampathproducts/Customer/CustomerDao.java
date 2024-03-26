package com.sampathproducts.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    // creating the code
    @Query(value = "select concat('cust/',lpad((substring(max(customer_code),(position('/'in max(customer_code)))+1, 3)+1),3,0)) FROM sampathproducts.customer_details;", nativeQuery = true)
    public String getNextCustomerCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.customer_details where customer_deleted=1 order by customer_id desc", nativeQuery = true)
    public List<Customer> getDeletedCustomer();

    // get non deleted values
    @Query(value = "select * from sampathproducts.customer_details where customer_deleted=0 order by customer_id desc", nativeQuery = true)
    public List<Customer> getExistingCustomer();

}
