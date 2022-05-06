package com.wangpeng.bms.service.impl;

import com.wangpeng.bms.mapper.UserMapper;
import com.wangpeng.bms.model.User;
import com.wangpeng.bms.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User login(String username, String password, Byte isAdmin) {
        return userMapper.selectByUsernameAndPasswordAndIsAdmin(username, password, isAdmin);
    }

    @Override
    public Integer register(String username, String password) {
        User tmp = userMapper.selectByUsername(username);
        if(tmp != null) return 0;  //账号重复

        User user = new User();
        user.setUsername(username);
        user.setUserpassword(password);
        user.setIsadmin((byte)0);
        return userMapper.insertSelective(user);
    }

    @Override
    public void setPassword(Integer id, String password) {
        User user = new User();
        user.setUserid(id);
        user.setUserpassword(password);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> queryUsersByPage(Integer page, Integer size) {
        return userMapper.selectAllByLimit((page - 1) * size, size);
    }

    @Override
    public Integer getCount() {
        return userMapper.selectCount();
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer deleteUser(User user) {
        if(user.getUserid() == 1) return 0;
        return userMapper.deleteByPrimaryKey(user.getUserid());
    }

    @Override
    public Integer deleteUsers(List<User> users) {
        int count = 0;
        for(User user : users) {
            count += deleteUser(user);
        }
        return count;
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> queryUsers() {
        return userMapper.selectAll();
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return userMapper.selectCountBySearch();
    }

    @Override
    public List<User> searchUsersByPage(Integer page, Integer size, Map<String, Object> searchParam) {
        searchParam.put("begin", (page - 1) * size);
        searchParam.put("size", size);
        return userMapper.selectBySearch(searchParam);
    }

}
