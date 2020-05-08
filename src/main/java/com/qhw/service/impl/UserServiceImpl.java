package com.qhw.service.impl;

import com.qhw.dao.UserRepository;
import com.qhw.pojo.User;
import com.qhw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus on 2020/2/26  13:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void save(User obj) {
        userRepository.save(obj);
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
