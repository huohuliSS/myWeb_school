package com.qhw.controller;

import com.qhw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asus on 2020/2/26  13:57
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;



}
