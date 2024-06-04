package com.baidu.supermarket.service.impl;

import com.baidu.supermarket.mapper.UserMapper;
import com.baidu.supermarket.pojo.User;
import com.baidu.supermarket.service.UserService;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    //用户登录
    public User login(User user){
        return userMapper.login(user);
    }

    //购买时用户登录
    public User userLogin(User user){
        return userMapper.userLogin(user);
    }
    //查询所有用户信息
    public PageResult<User> getAllUser(String name,int currentPage,int pageSize){
        PageResult<User> pageResult=new PageResult<>();
        //设置查询成功
        pageResult.setMsg("查询成功");
        //设置查询成功的状态码为0
        pageResult.setCode(0);

        //设置查询总记录数，用于进行分页计算
        int total=userMapper.count(name);
        pageResult.setCount(total);

        //设置查询结果
        int start=(currentPage-1)*pageSize;
        List<User> list=userMapper.getAllUser(name,start,pageSize);
        pageResult.setData(list);

        return pageResult;
    }

    //根据主键批量删除用户信息
    @Transactional
    public int batchDelUser(Integer ids[]){
        return userMapper.batchDelUser(ids);
    }

    //添加用户信息
    @Transactional
    public int addUser(User user){
        return userMapper.addUser(user);
    }
    //根据id查询指定的用户信息
    public User getUserById(int uid){
        return userMapper.getUserById(uid);
    }


    //根据主键修改指定的用户信息
    @Transactional
    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    //根据主键删除指定的用户信息
    @Transactional
    public int deleteUserByUid(int uid){
        return userMapper.deleteUserByUid(uid);
    }
}
