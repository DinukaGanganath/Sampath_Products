package com.sampathproducts.ProductType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductTypeDao extends JpaRepository<ProductType, Integer> {
    @Query(value = "select concat('ptype/',lpad((substring(max(producttype_code),(position('/'in max(producttype_code)))+1, 3)+1),3,0)) FROM sampathproducts.producttype_details;", nativeQuery = true)
    public String getNextProductTypeCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.producttype_details where producttype_deleted=1 order by producttype_id desc", nativeQuery = true)
    public List<ProductType> getDeletedProductType();

    // get non deleted values
    @Query(value = "select * from sampathproducts.producttype_details where producttype_deleted=0 order by producttype_id desc", nativeQuery = true)
    public List<ProductType> getExistingProductType();
}
