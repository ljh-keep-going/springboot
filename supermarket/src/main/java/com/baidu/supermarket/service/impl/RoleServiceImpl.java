package com.baidu.supermarket.service.impl;

import com.baidu.supermarket.mapper.RoleMapper;
import com.baidu.supermarket.pojo.Role;
import com.baidu.supermarket.service.RoleService;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired(required = false)
    private RoleMapper roleMapper;

    //查询所有角色
    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }

    //分页以及模糊分页查询所有角色
    public PageResult<Role> getAllRolesByPage(String roleName, int currentPage, int pageSize){
        //计算起始位置
        int start=(currentPage-1)*pageSize;

        //执行查询
        List<Role> list=roleMapper.getAllRolesByPage(roleName,start,pageSize);

        //封装结果
        PageResult<Role> pageResult=new PageResult<>();

        pageResult.setCode(0);
        pageResult.setMsg("查询成功");
        //设置总记录数
        int count=roleMapper.count(roleName);
        pageResult.setCount(count);

        //设置数据
        pageResult.setData(list);
        return pageResult;
    }

    //添加角色
    public int addRole(Role role){
        return roleMapper.addRole(role);
    }

    //批量删除角色
    public int batchDelRole(Integer rids[]){
        return roleMapper.batchDelRole(rids);
    }

    //根据主键查询指定的角色
    public Role getRoleByRid(int rid){
        return roleMapper.getRoleByRid(rid);
    }

    //根据主键修改指定角色
    public int updateRole(Role role){
        return roleMapper.updateRole(role);
    }

    //根据主键删除角色
    public int deleteRoleByRid(int rid){
        return roleMapper.deleteRoleByRid(rid);
    }
}
