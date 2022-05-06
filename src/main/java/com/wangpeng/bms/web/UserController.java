package com.wangpeng.bms.web;

import com.wangpeng.bms.model.User;
import com.wangpeng.bms.service.UserService;
import com.wangpeng.bms.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    // 登录
    @RequestMapping(value = "/login")
    public Integer login(String username, String password, String authority, HttpServletRequest req){
        // res：0 账号或密码错误，1 登录成功
        byte isAdmin = (byte) (authority.equals("manager") ? 1 : 0);
        User user = userService.login(username, password, isAdmin);
        if(user == null) return 0;
        // 存session
        req.getSession().setAttribute("userObj", user);
        return 1;
    }

    // 注册
    @RequestMapping(value = "/register")
    public Integer register(String username, String password){
        // res：0 用户名重复，1 注册成功
        return userService.register(username, password);
    }

    // 获取用户
    @RequestMapping(value = {
            "/getUser",
            "/reader/getUser"
    })
    public User getUser(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("userObj");
    }

    // 退出登录
    @RequestMapping(value = {
            "/exitLogin",
            "/reader/exitLogin"
    })
    public void exitLogin(HttpServletRequest req){
        // 清除session
        req.getSession().removeAttribute("userObj");
    }

    // 修改密码
    @RequestMapping(value = {
            "/alterPassword",
            "reader/alterPassword"
    })
    public Integer alterPassword(String oldPassword, String newPassword,HttpServletRequest req){
        //获取当前账号信息
        User userObj =  (User) req.getSession().getAttribute("userObj");

        //检查旧密码是否正确
        User user = userService.login(userObj.getUsername(), oldPassword, userObj.getIsadmin());
        if(user == null) {  //旧密码不正确
            return 0;
        } else {    //旧密码正确，设置新密码
            userService.setPassword(userObj.getUserid(), newPassword);
            return 1;
        }

    }

    // 分页查询用户
    @GetMapping(value = "/queryUsersByPage")
    public Map<String, Object> queryUsersByPage(Integer page, Integer limit){
        // 获取数量
        Integer count = userService.getCount();

        // 获取数据
        List<User> users = userService.queryUsersByPage(page, limit);

        // 结果map
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("code", 0);
        res.put("msg", "success");
        res.put("count", count);
        res.put("data", users);
        return res;
    }

    // 查询所有用户
    @GetMapping(value = "/queryUsers")
    public List<User> queryUsers(){
        return userService.queryUsers();
    }

    // 添加用户
    @PostMapping(value = "/addUser")
    public Integer addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    // 获得数量
    @GetMapping(value = "/getCount")
    public Integer getCount(){
        return userService.getCount();
    }

    // 删除用户
    @DeleteMapping(value = "/deleteUser")
    public Integer deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }

    // 删除一些用户
    @DeleteMapping(value = "/deleteUsers")
    public Integer deleteUsers(@RequestBody List<User> users){
        return userService.deleteUsers(users);
    }

    // 更新用户
    @RequestMapping(value = "/updateUser")
    public Integer updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    // 搜索用户
    @GetMapping("/searchUsersByPage")
    public Map<String,Object> searchBookTypesByPage(Integer page, Integer limit, String json){
        //获得搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = userService.getSearchCount(searchParam);
        //查询数据
        List<User> users = userService.searchUsersByPage(page, limit, searchParam);
        //结果map
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("code", 0);
        res.put("msg", "success");
        res.put("count", count);
        res.put("data", users);
        return res;
    }
}
