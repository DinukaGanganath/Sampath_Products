package com.sampathproducts.Material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterialDao extends JpaRepository<Material, Integer> {
    @Query(value = "select concat('mat/',lpad((substring(max(material_code),(position('/'in max(material_code)))+1, 3)+1),3,0)) FROM sampathproducts.material_details;", nativeQuery = true)
    public String getNextMaterialCode();
}
