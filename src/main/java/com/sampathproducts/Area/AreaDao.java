package com.sampathproducts.Area;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AreaDao extends JpaRepository<Area, Integer> {
    @Query(value = "select concat('area/',lpad((substring(max(area_code),(position('/'in max(area_code)))+1, 3)+1),3,0)) FROM sampathproducts.area_details;", nativeQuery = true)
    public String getNextAreaCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.area_details where area_deleted=1 order by area_id desc", nativeQuery = true)
    public List<Area> getDeletedArea();

    // get non deleted values
    @Query(value = "select * from sampathproducts.area_details where area_deleted=0 order by area_id desc", nativeQuery = true)
    public List<Area> getExistingArea();
}
