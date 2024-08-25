package com.sampathproducts.Module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModuleDao extends JpaRepository<Module, Integer> {
    @Query(value = "select * from sampathproducts.module where module_id=(select max(module_id) from sampathproducts.module);", nativeQuery = true)
    public Module getLatestModule();
}
