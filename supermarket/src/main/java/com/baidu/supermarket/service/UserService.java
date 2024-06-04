package com.baidu.supermarket.service;

import com.baidu.supermarket.pojo.User;
import com.baidu.supermarket.util.PageResult;

public interface UserService {
    //用户登录
    public User login(User user);

    //购买时用户登录
     public User userLogin(User user);

    //查询所有用户信息
    public PageResult<User> getAllUser(String name,int currentPage,int pageSize);

    //根据主键批量删除用户信息
    public int batchDelUser(Integer ids[]);

    //添加用户信息
    public int addUser(User user);

    //根据id查询指定的用户信息
     public User getUserById(int uid);


    //根据主键修改指定的用户信息
    public int updateUser(User user);

    //根据主键删除指定的用户信息
    public int deleteUserByUid(int uid);
}
