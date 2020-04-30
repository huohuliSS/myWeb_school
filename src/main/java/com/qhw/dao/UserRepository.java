package com.qhw.dao;

import com.qhw.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by asus on 2020/2/26  10:52
 */
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

    User findUserByUsername(String username);

}
