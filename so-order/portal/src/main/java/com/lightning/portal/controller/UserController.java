package com.lightning.portal.controller;

import com.google.gson.Gson;
import com.lightning.portal.bean.Folder;
import com.lightning.portal.bean.Myfile;
import com.lightning.portal.service.UserService;
import com.lightning.portal.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author gyf
 * @Date 2021-01-31 20:54
 * @ClassName UserController
 * @Description
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    //    用户注册
    @GetMapping("/register")
    public String register(@RequestParam("userId") String userId,@RequestParam("username") String username, @RequestParam("password") String password) {
        return Results.myResult(userService.register(userId,username,password));
    }

    //    用户登录
    @RequestMapping("/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        return userService.login(userId,password);
    }

}
