package com.wangpeng.bms.service;

import com.wangpeng.bms.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @param isAdmin
     * @return
     */
    User login(String username, String password, Byte isAdmin);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    Integer register(String username, String password);

    /**
     * 设置密码
     * @param id
     * @param password
     */
    void setPassword(Integer id, String password);

    List<User> queryUsersByPage(Integer page, Integer size);

    Integer getCount();

    Integer addUser(User user);

    Integer deleteUser(User user);

    Integer deleteUsers(List<User> users);

    Integer updateUser(User user);

    List<User> queryUsers();

    int getSearchCount(Map<String, Object> searchParam);

    List<User> searchUsersByPage(Integer page, Integer size, Map<String, Object> searchParam);
}
