package com.sampathproducts.ModuleRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRoleDao extends JpaRepository<ModuleRole, Integer> {
    @Query(value = "select * from sampathproducts.module_has_role where module_id = :module_id and role_id = :role_id ", nativeQuery = true)
    public ModuleRole getModuleRole(@Param("module_id") Integer module_id, @Param("role_id") Integer role_id);
}
