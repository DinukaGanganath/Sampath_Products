package com.sampathproducts.Vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleDao extends JpaRepository<Vehicle, Integer> {

    // creating the code
    @Query(value = "select concat('veh/',lpad((substring(max(vehicle_code),(position('/'in max(vehicle_code)))+1, 3)+1),3,0)) FROM sampathproducts.vehicle_details;", nativeQuery = true)
    public String getNextVehicleCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.vehicle_details where vehicle_deleted=1 order by vehicle_id desc", nativeQuery = true)
    public List<Vehicle> getDeletedVehicle();

    // get non deleted values
    @Query(value = "select * from sampathproducts.vehicle_details where vehicle_deleted=0 order by vehicle_id desc", nativeQuery = true)
    public List<Vehicle> getExistingVehicle();

}
