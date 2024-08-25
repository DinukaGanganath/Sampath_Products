package com.sampathproducts.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RoleDao extends JpaRepository<Role, Integer> {
    @Query(value = "select * from sampathproducts.role", nativeQuery = true)
    public List<Role> getAllRoles();
}
