package com.baidu.springboot07.controller;


import com.baidu.springboot07.pojo.Role;
import com.baidu.springboot07.pojo.User;
import com.baidu.springboot07.service.RoleService;
import com.baidu.springboot07.service.UserService;
import com.baidu.springboot07.utils.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    //跳转到普通用户的登录页面
    @RequestMapping("/toUserLogin")
    public String toUserLogin(){
        System.out.println("---------toUserLogin---------");

        return "user/userLogin";
    }

    //跳转到管理员的登录页面
    @Operation(summary = "跳转到登录页面")
    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request){

        System.out.println("----------toLogin----------");

        //查询所有角色
        List<Role> list=roleService.list();

        //将集合保存到request域中
        request.setAttribute("rolelist",list);

        return "user/login";

    }

    //处理用户登录
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(User user, HttpSession session){
        System.out.println("-----------login---------");
        System.out.println("user:"+user);

        //根据用户名，密码，角色主键查询指定的用户信息
        User user2=userService.login(user);

        if(user2!=null){ //登录成功

            //将当前登录的用户信息保存到session中
            session.setAttribute("user2",user2);

            return AjaxResult.right();
        }else { //登录失败
            return AjaxResult.error();
        }
    }
}
