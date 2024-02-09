package com.sampathproducts.Area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AreaDao extends JpaRepository<Area, Integer> {
    @Query(value = "select concat('area/',lpad((substring(max(area_code),(position('/'in max(area_code)))+1, 3)+1),3,0)) FROM sampathproducts.area_details;", nativeQuery = true)
    public String getNextAreaCode();
}
