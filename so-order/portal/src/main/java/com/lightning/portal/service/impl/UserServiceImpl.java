package com.lightning.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
}
