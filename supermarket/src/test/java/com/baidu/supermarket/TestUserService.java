package com.baidu.supermarket;

import com.baidu.supermarket.pojo.User;
import com.baidu.supermarket.service.UserService;
import com.baidu.supermarket.util.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    //测试查询全部
    @Test
    public void testGetAllUser(){
        PageResult<User> pageResult=userService.getAllUser("",1,5);
        System.out.println("pageResult:"+pageResult);
        List<User> list=pageResult.getData();
        for(User user:list){
            System.out.println("user:"+user);
        }
    }

    //测试批量删除
    @Test
    public void testBatchDelUser(){
        Integer ids[]={3,5};

        int total=userService.batchDelUser(ids);
        System.out.println("total:"+total);
    }

    //测试添加用户
    @Test
    public void testAddUser(){
        User user=new User();
        user.setName("张三");
        user.setPassword("张三");
        user.setCreateTime(new Date());
        user.setRoleId(1);
        user.setTelephone(12312312312L);

        int total=userService.addUser(user);
        System.out.println("total:"+total);
    }

    @Test
    public void testGetUserById(){
        User user=userService.getUserById(10);
        System.out.println("user:"+user);
    }

    @Test
    public void testGetUserById2(){
        User user=userService.getUserById(10);

    }
}
