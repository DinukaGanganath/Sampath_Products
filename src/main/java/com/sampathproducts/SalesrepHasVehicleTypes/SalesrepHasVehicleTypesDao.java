package com.sampathproducts.SalesrepHasVehicleTypes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalesrepHasVehicleTypesDao extends JpaRepository<SalesrepHasVehicleTypes, Integer> {

    // creating the code
    @Query(value = "select concat('rep/',lpad((substring(max(salesrep_code),(position('/'in max(salesrep_code)))+1, 3)+1),3,0)) FROM sampathproducts.salesrep_details;", nativeQuery = true)
    public String getNextSalesrepHasVehicleTypesCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.salesrep_details where salesrep_deleted=1 order by salesrep_id desc", nativeQuery = true)
    public List<SalesrepHasVehicleTypes> getDeletedSalesrepHasVehicleTypes();

    // get non deleted values
    @Query(value = "select * from sampathproducts.salesrep_details where salesrep_deleted=0 order by salesrep_id desc", nativeQuery = true)
    public List<SalesrepHasVehicleTypes> getExistingSalesrepHasVehicleTypes();

}
