package com.baidu.springboot07.service.impl;

import com.baidu.springboot07.pojo.Role;
import com.baidu.springboot07.dao.RoleMapper;
import com.baidu.springboot07.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
