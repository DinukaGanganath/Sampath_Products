package com.sampathproducts.PurchaseOrder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Integer> {

    // get Deleted values
    @Query(value = "select * from sampathproducts.purchaseorder where purchaseorder_status=0 order by purchaseorder_id desc", nativeQuery = true)
    public List<PurchaseOrder> getDeletedPurchaseOrder();

    // get non deleted values
    @Query(value = "select * from sampathproducts.purchaseorder where purchaseorder_status=1 order by purchaseorder_id desc", nativeQuery = true)
    public List<PurchaseOrder> getExistingPurchaseOrder();

}
