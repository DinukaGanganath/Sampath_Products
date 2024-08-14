package com.sampathproducts.Dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DashboardDao extends JpaRepository<Dashboard, Integer> {
    @Query(value = "select concat('dashboard/',lpad((substring(max(dashboard_code),(position('/'in max(dashboard_code)))+1, 3)+1),3,0)) FROM sampathproducts.dashboard_details;", nativeQuery = true)
    public String getNextDashboardCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.dashboard_details where dashboard_deleted=1 order by dashboard_id desc", nativeQuery = true)
    public List<Dashboard> getDeletedDashboard();

    // get non deleted values
    @Query(value = "select * from sampathproducts.dashboard_details where dashboard_deleted=0 order by dashboard_id desc", nativeQuery = true)
    public List<Dashboard> getExistingDashboard();
}
