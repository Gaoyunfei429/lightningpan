package com.lightning.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.lightning.portal.bean.Folder;
import com.lightning.portal.bean.User;
import com.lightning.portal.mapper.FolderMapper;
import com.lightning.portal.mapper.UserMapper;
import com.lightning.portal.service.UserService;
import com.lightning.portal.util.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    FolderMapper folderMapper;

    @Override
    public String register(String userId,String username, String password) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(username);
        user.setPassword(password);
        if (0 == userMapper.insert(user)) {
            return "false";
        }
        File file = new File(PathUtils.BASEPATH + "/" + username);
        if (!file.exists()) {
            file.mkdir();
            Folder folder = new Folder();
            folder.setParentId(-1);
            folder.setUserId(userId);
            folder.setFolderName(username);
            folder.setTime(new Date(System.currentTimeMillis()));
            folderMapper.insert(folder);
            return "true";
        }
        return "false";
    }

    @Override
    public String login(String userId, String password) {
        Map<String, Object> som = new HashMap<>();
        Gson gson = new Gson();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userId",userId);
        User user = userMapper.selectOne(wrapper);
        if (null == user) {
            som.put("code", 500);
            som.put("data", "false");
            som.put("msg", "error");
        } else if (user.getPassword().equals(password)) {
            Map<String, Object> data = new HashMap<>();
            int folderId = folderMapper.selectFolderIdByUserId(user.getUserId());
            data.put("baseFolderId", folderId);
            data.put("user", user);
            som.put("code", 200);
            som.put("data", data);
            som.put("msg", "success");
        } else {
            som.put("code", 500);
            som.put("data", "false");
            som.put("msg", "error");
        }
        return gson.toJson(som);
    }
}
