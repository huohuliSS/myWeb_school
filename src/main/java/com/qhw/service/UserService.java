package com.qhw.service;


import com.qhw.pojo.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by asus on 2020/2/26  13:14
 */
public interface UserService {

    //1.查询所有
    List<User> findAll();

    //2.根据主键查询单个对象。
    User getOne(Integer id);

    User findUserByUsername(String username);

    //4.添加单个对象
    void save(User obj);

    //5.修改单个对象
    void update(User obj);

    //6.根据主键删除单个对象。
    void deleteById(Integer id);


}
