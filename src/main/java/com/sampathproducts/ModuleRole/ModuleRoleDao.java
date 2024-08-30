package com.sampathproducts.ModuleRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRoleDao extends JpaRepository<ModuleRole, Integer> {
    @Query(value = "select * from sampathproducts.module_has_role where module_id = :module_id and role_id = :role_id ", nativeQuery = true)
    public ModuleRole getModuleRole(@Param("module_id") Integer module_id, @Param("role_id") Integer role_id);

    @Query(value = "SELECT bit_or(p.sel) as sel, bit_or(p.cre) as cre, bit_or(p.upd) as upd, bit_or(p.del) as del FROM sampathproducts.module_has_role as p Where p.role_id in (select uhr.role_id from sampathproducts.user_has_role as uhr where uhr.user_id in (select user_id from sampathproducts.user as u where u.user_name=?1)) and p.module_id in (select module_id from sampathproducts.module as m where m.module_name=?2);", nativeQuery = true)
    public String getPrivilageByUserModule(String username, String modulename);
}
