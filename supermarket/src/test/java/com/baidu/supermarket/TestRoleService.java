package com.baidu.supermarket;

import com.baidu.supermarket.pojo.Role;
import com.baidu.supermarket.service.RoleService;
import com.baidu.supermarket.util.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestRoleService {

    @Autowired
    private RoleService roleService;

    @Test
    public void testGetAllRoles(){
        List<Role> list=roleService.getAllRoles();
        for(Role role:list){
            System.out.println("role:"+role);
        }
    }

    //测试分页查询
    @Test
    public void testGetRoleByPage(){
        PageResult<Role> result=roleService.getAllRolesByPage("",1,3);
        System.out.println("result:"+result);
    }
}
