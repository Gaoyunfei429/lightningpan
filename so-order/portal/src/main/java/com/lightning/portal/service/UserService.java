package com.lightning.portal.service;

/**
 * @Author gyf
 * @Date 2021-01-31 23:53
 * @ClassName UserService
 * @Description
 */
public interface UserService{
    String register(String userId,String username, String password);

    String login(String userId, String password);
}
