package com.qhw.controller;

import com.qhw.common.Result;
import com.qhw.pojo.User;
import com.qhw.service.UserService;
import com.qhw.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by asus on 2020/2/26  13:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser")
    public Result<User> getUser(HttpServletResponse response) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return new Result<>(true, "查询当前用户成功", user);
        }
        return new Result<>(false, "查询当前用户失败");
    }

    @RequestMapping("/updateUserInfo")
    public Result updateUserInfo(User user) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            if (username.equals(user.getUsername())) {
                User userByUsername = userService.findUserByUsername(username);
                userByUsername.setPhone(user.getPhone());
                userByUsername.setRemark(user.getRemark());
                userService.save(userByUsername);
            }
            return new Result(true, "修改当前用户：" + username + "信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "自改当前用户：" + username + "信息失败");
        }
    }

    @RequestMapping("/updatePassword")
    public Result updatePassword(User user, @RequestParam("newPassword1") String newPassword1) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            if (username.equals(user.getUsername())) {
                User userByUsername = userService.findUserByUsername(username);
                if (userByUsername.getPassword().equals(MD5Utils.code(user.getPassword()))) {
                    userByUsername.setPassword(MD5Utils.code(newPassword1));
                    userService.save(userByUsername);
                    return new Result(true, "修改密码成功");
                }else {
                    return new Result(false,"原密码不正确");
                }
            }
            return new Result(true, "修改：" + username + "密码成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "自改当前用户：" + username + "密码失败");
        }
    }


}
