package com.sampathproducts.Material;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterialDao extends JpaRepository<Material, Integer> {
    @Query(value = "select concat('mat/',lpad((substring(max(material_code),(position('/'in max(material_code)))+1, 3)+1),3,0)) FROM sampathproducts.material_details;", nativeQuery = true)
    public String getNextMaterialCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.material_details where material_deleted=1 order by material_id desc", nativeQuery = true)
    public List<Material> getDeletedMaterial();

    // get non deleted values
    @Query(value = "select * from sampathproducts.material_details where material_deleted=0 order by material_id desc", nativeQuery = true)
    public List<Material> getExistingMaterial();
}
