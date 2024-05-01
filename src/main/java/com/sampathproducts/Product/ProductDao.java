package com.sampathproducts.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    // creating the code
    @Query(value = "select concat('prod/',lpad((substring(max(product_code),(position('/'in max(product_code)))+1, 3)+1),3,0)) FROM sampathproducts.product_details;", nativeQuery = true)
    public String getNextProductCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.product_details where product_deleted=1 order by product_id desc", nativeQuery = true)
    public List<Product> getDeletedProduct();

    // get non deleted values
    @Query(value = "select * from sampathproducts.product_details where product_deleted=0 order by product_id desc", nativeQuery = true)
    public List<Product> getExistingProduct();
}
