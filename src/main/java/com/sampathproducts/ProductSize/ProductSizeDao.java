package com.sampathproducts.ProductSize;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductSizeDao extends JpaRepository<ProductSize, Integer> {
    @Query(value = "select concat('ptype/',lpad((substring(max(productsize_code),(position('/'in max(productsize_code)))+1, 3)+1),3,0)) FROM sampathproducts.productsize_details;", nativeQuery = true)
    public String getNextProductSizeCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.productsize_details where productsize_deleted=1 order by productsize_id desc", nativeQuery = true)
    public List<ProductSize> getDeletedProductSize();

    // get non deleted values
    @Query(value = "select * from sampathproducts.productsize_details where productsize_deleted=0 order by productsize_id desc", nativeQuery = true)
    public List<ProductSize> getExistingProductSize();
}
