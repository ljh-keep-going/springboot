package com.baidu.springboot07.service.impl;

import com.baidu.springboot07.pojo.User;
import com.baidu.springboot07.dao.UserMapper;
import com.baidu.springboot07.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //根据用户名，密码，角色主键查询指定的用户信息
    @Autowired
    private UserMapper userMapper;
    public User login(User user){
        //创建QueryWrapper对象
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        //封装条件
        queryWrapper.eq("name",user.getName());
        queryWrapper.eq("password",user.getPassword());
        queryWrapper.eq("role_id",user.getRoleId());

        //执行查询
        User user2=userMapper.selectOne(queryWrapper);

        return user2;
    }
}
