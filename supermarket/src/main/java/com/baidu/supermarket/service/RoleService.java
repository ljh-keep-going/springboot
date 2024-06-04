package com.baidu.supermarket.service;

import com.baidu.supermarket.pojo.Role;
import com.baidu.supermarket.util.PageResult;

import java.util.List;

public interface RoleService {

    //查询所有角色
    public List<Role> getAllRoles();

    //分页以及模糊分页查询所有角色
    public PageResult<Role> getAllRolesByPage(String roleName, int currentPage, int pageSize);

    //添加角色
    public int addRole(Role role);

    //批量删除角色
    public int batchDelRole(Integer rids[]);

    //根据主键查询指定的角色
    public Role getRoleByRid(int rid);

    //根据主键修改指定角色
    public int updateRole(Role role);

    //根据主键删除角色
    public int deleteRoleByRid(int rid);
}
