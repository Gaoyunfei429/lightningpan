package com.lightning.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lightning.portal.bean.User;
import com.lightning.portal.mapper.UserMapper;
import com.lightning.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author gyf
 * @Date 2021-01-31 20:52
 * @ClassName UserServiceImpl
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String register(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        if (0 == userMapper.insert(user)) {
            return "false";
        }
        return "true";
    }

    @Override
    public String login(String username, String password) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (null == user) {
            return "false";
        }
        if (user.getPassword().equals(password)) {
            return "true";
        }else {
            return "false";
        }
    }
}
