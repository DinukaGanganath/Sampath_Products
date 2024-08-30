package com.sampathproducts.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sampathproducts.Batch.Batch;
import com.sampathproducts.Request.Request;

import java.util.List;

public interface ReportDao extends JpaRepository<Batch, Integer> {

    @Query(value = "SELECT b.batch_code, bhp.batch_product_expired, ptd.producttype_name, psd.productsize_name FROM batch b JOIN batch_has_products bhp ON b.batch_id = bhp.batch_id JOIN product_details pd ON bhp.product_id = pd.product_id JOIN producttype_details ptd ON pd.producttype_id = ptd.producttype_id JOIN productsize_details psd ON pd.productsize_id = psd.productsize_id WHERE bhp.qty > 0;", nativeQuery = true)
    public String[][] batchDetailsList();

    // @Query(value = "select e from sampathproducts.request_quotation q where
    // q.supplier_id=?1")
    // public List<Request> requestListBySupplier(int supplier);

    // @Param("user_name") String user_name

}
