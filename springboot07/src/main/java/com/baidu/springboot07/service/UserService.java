package com.baidu.springboot07.service;

import com.baidu.springboot07.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
public interface UserService extends IService<User> {

    // 根据用户名，密码，角色主键查询指定的用户信息
    public User login(User user);
}
