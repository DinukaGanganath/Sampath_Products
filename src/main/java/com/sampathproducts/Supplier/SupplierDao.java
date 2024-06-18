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

    // get supplier better to make quotation
    @Query(value = "select * from sampathproducts.supplier_details where supplier_id not in (select supplier_id from sampathproducts.request_quotation where ((DATE_ADD(request_created_date, Interval (request_validity-3) DAY) > CURDATE()) and request_created=1 and request_deleted=0) or (request_created=0) order by request_id desc);", nativeQuery = true)
    public List<Supplier> getQuotationSupplier();

}
