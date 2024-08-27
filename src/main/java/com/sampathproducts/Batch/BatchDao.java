package com.sampathproducts.Batch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BatchDao extends JpaRepository<Batch, Integer> {

    // get Deleted values
    @Query(value = "select * from sampathproducts.purchaseorder where purchaseorder_status=0 order by purchaseorder_id desc", nativeQuery = true)
    public List<Batch> getDeletedBatch();

    // get non deleted values
    @Query(value = "select * from sampathproducts.purchaseorder where purchaseorder_status=1 order by purchaseorder_id desc", nativeQuery = true)
    public List<Batch> getExistingBatch();

    @Query(value = "select concat('batch/',lpad((substring(max(batch_code),(position('/'in max(batch_code)))+1, 3)+1),3,0)) FROM sampathproducts.batch;", nativeQuery = true)
    public String getNextBatchCode();

}
