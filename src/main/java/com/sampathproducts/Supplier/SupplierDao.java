package com.sampathproducts.Supplier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierDao extends JpaRepository<Supplier, Integer> {

    // creating the code
    @Query(value = "select concat('sup/',lpad((substring(max(supplier_code),(position('/'in max(supplier_code)))+1, 3)+1),3,0)) FROM sampathproducts.supplier_details;", nativeQuery = true)
    public String getNextSupplierCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.supplier_details where supplier_deleted=1 order by supplier_id desc", nativeQuery = true)
    public List<Supplier> getDeletedSupplier();

    // get non deleted values
    @Query(value = "select * from sampathproducts.supplier_details where supplier_deleted=0 order by supplier_id desc", nativeQuery = true)
    public List<Supplier> getExistingSupplier();

}
