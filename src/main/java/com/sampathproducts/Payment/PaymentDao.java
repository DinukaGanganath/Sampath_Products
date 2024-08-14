package com.sampathproducts.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
    @Query(value = "select concat('payment/',lpad((substring(max(payment_code),(position('/'in max(payment_code)))+1, 3)+1),3,0)) FROM sampathproducts.payment_details;", nativeQuery = true)
    public String getNextPaymentCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.payment_details where payment_deleted=1 order by payment_id desc", nativeQuery = true)
    public List<Payment> getDeletedPayment();

    // get non deleted values
    @Query(value = "select * from sampathproducts.payment_details where payment_deleted=0 order by payment_id desc", nativeQuery = true)
    public List<Payment> getExistingPayment();
}
