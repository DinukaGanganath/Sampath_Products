package com.sampathproducts.PurchaseOrderHasQuotation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseOrderHasQuotationDao extends JpaRepository<PurchaseOrderHasQuotation, Integer> {

    @Query(value = "select * from sampathproducts.purchase_order_has_quotation where status='ordered' order by order_line_id desc", nativeQuery = true)
    public List<PurchaseOrderHasQuotation> getPurchaseOrdered();
}
