package com.sampathproducts.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer> {

    // get Deleted values
    @Query(value = "select * from sampathproducts.user_details where user_status=1 order by user_id desc", nativeQuery = true)
    public List<User> getDeletedUser();

    // get non deleted values
    @Query(value = "select * from sampathproducts.user_details where user_status=0 order by user_id desc", nativeQuery = true)
    public List<User> getExistingUser();

}
