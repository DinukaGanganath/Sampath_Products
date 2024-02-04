package com.sampathproducts.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierDao extends JpaRepository<Supplier, Integer> {

    @Query(value = "select concat('sup/',lpad((substring(max(supplier_code),(position('/'in max(supplier_code)))+1, 3)+1),3,0)) FROM sampathproducts.supplier_details;", nativeQuery = true)
    public String getNextSupplierCode();

}
