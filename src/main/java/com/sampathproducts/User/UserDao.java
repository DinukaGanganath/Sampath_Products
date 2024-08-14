package com.sampathproducts.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer> {

    // get Deleted values
    @Query(value = "select * from sampathproducts.user where user_status=0 order by user_id desc", nativeQuery = true)
    public List<User> getDeletedUser();

    // get non deleted values
    @Query(value = "select * from sampathproducts.user where user_status=1 order by user_id desc", nativeQuery = true)
    public List<User> getExistingUser();

    @Query(value = "select * from sampathproducts.user where employee_id = :employee_id", nativeQuery = true)
    public User getUserByEmployee(@Param("employee_id") Integer employee_id);

}
