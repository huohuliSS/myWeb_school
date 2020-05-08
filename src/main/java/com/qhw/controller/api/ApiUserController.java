package com.qhw.controller.api;

import com.qhw.common.Result;
import com.qhw.pojo.User;
import com.qhw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asus on 2020/5/4  10:25
 */
@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("findUserById/{id}")
    public Result<User> findUserById(@PathVariable("id") Integer id){
        User user = userService.getOne(id);
        if (user != null) {
            return new Result<>(true, "查询用户成功", user);
        }
        return new Result<>(false, "查询用户失败");
    }
}
