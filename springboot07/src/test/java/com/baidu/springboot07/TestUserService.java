package com.baidu.springboot07;

import com.baidu.springboot07.pojo.User;
import com.baidu.springboot07.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testLogin(){
        System.out.println(userService);
        User user=new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setRoleId(1);

        User user2=userService.login(user);
        System.out.println("user2:"+user2);
    }

}
