package com.sampathproducts.Division;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DivisionDao extends JpaRepository<Division, Integer> {

    @Query(value = "select concat('div/',lpad((substring(max(postal_division_code),(position('/'in max(postal_division_code)))+1, 3)+1),3,0)) FROM sampathproducts.postal_division;", nativeQuery = true)
    public String getNextDivisionCode();
}
