package com.sampathproducts.CustomerOrder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerOrderDao extends JpaRepository<CustomerOrder, Integer> {
    @Query(value = "select concat('cord/',lpad((substring(max(customer_order_code),(position('/'in max(customer_order_code)))+1, 3)+1),3,0)) FROM sampathproducts.customer_order_details;", nativeQuery = true)
    public String getNextCustomerOrderCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.customer_order_details where customer_order_status='created' order by customer_order_id desc", nativeQuery = true)
    public List<CustomerOrder> getCreatedCustomerOrder();

    @Query(value = "select * from sampathproducts.customer_order_details where customer_order_status='progress' order by customer_order_id desc", nativeQuery = true)
    public List<CustomerOrder> getReadyCustomerOrder();

    @Query(value = "select * from sampathproducts.customer_order_details where customer_order_status='shipped' order by customer_order_id desc", nativeQuery = true)
    public List<CustomerOrder> getShippedCustomerOrder();

    @Query(value = "select * from sampathproducts.customer_order_details where customer_order_status='delivered' order by customer_order_id desc", nativeQuery = true)
    public List<CustomerOrder> getDeliveredCustomerOrder();

    // get non deleted values
    @Query(value = "select * from sampathproducts.customer_order_details where customer_order_created=0 and customer_order_deleted=0 order by customer_order_id desc", nativeQuery = true)
    public List<CustomerOrder> getRequestedCustomerOrder();

    // get expired quotation
    @Query(value = "select * from sampathproducts.customer_order_details where customer_order_needed < CURDATE() order by customer_order_id desc;", nativeQuery = true)
    public List<CustomerOrder> getExpiredCustomerOrder();

    // get ending quotations
    @Query(value = "select * from sampathproducts.customer_order_details;", nativeQuery = true)
    public List<CustomerOrder> getEndingCustomerOrder();

    // get valid quotations
    @Query(value = "select * from sampathproducts.customer_order_details;", nativeQuery = true)
    public List<CustomerOrder> getvalidCustomerOrder();
}
