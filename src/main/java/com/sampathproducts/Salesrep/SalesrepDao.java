package com.sampathproducts.Salesrep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalesrepDao extends JpaRepository<Salesrep, Integer> {

    // creating the code
    @Query(value = "select concat('rep/',lpad((substring(max(salesrep_code),(position('/'in max(salesrep_code)))+1, 3)+1),3,0)) FROM sampathproducts.salesrep_details;", nativeQuery = true)
    public String getNextSalesrepCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.salesrep_details where salesrep_deleted=1 order by salesrep_id desc", nativeQuery = true)
    public List<Salesrep> getDeletedSalesrep();

    // get non deleted values
    @Query(value = "select * from sampathproducts.salesrep_details where salesrep_deleted=0 order by salesrep_id desc", nativeQuery = true)
    public List<Salesrep> getExistingSalesrep();

}
