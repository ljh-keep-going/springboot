package com.baidu.springboot07.controller;


import com.baidu.springboot07.utils.SysMenu;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MainController {

//    跳转到登录首页
    @RequestMapping("/toIndex")
    public String toContent(HttpServletRequest request) {
        //保存左侧功能栏
        request.setAttribute("userMenuList",getSysMenu());
        return "content";
    }


    //返回欢迎页面
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    //  临时菜单
    private List<SysMenu> getSysMenu() {
        List<SysMenu> ml = new ArrayList<SysMenu>();
        SysMenu m1 = new SysMenu("system", "系统管理", "layui-icon layui-icon-util", false, null);
        SysMenu m1_1 = new SysMenu("system", "用户管理", "layui-icon layui-icon-set ", true, "/user/toUserList");
        SysMenu m1_2 = new SysMenu("system", "角色管理", "layui-icon layui-icon-set ", true, "/role/toRoleList");
        SysMenu m1_3 = new SysMenu("system", "商品管理", "layui-icon layui-icon-set", true, "/prod/toList");
        SysMenu m1_4 = new SysMenu("system", "购物车管理", "layui-icon layui-icon-set ", true, "/cart/toCartList");
        SysMenu m1_5 = new SysMenu("system", "订单管理", "layui-icon layui-icon-set ", true, "/dict/toList");
        SysMenu m1_6 = new SysMenu("system", "订单月度分析", "layui-icon layui-icon-set", true, "/prod/toView1");
        SysMenu m1_7 = new SysMenu("system", "订单占比分析", "layui-icon layui-icon-set", true, "/prod/toView2");
        m1.getChildMenuList().add(m1_1);
        m1.getChildMenuList().add(m1_2);
        m1.getChildMenuList().add(m1_3);
        m1.getChildMenuList().add(m1_4);
        m1.getChildMenuList().add(m1_5);
        m1.getChildMenuList().add(m1_6);
        m1.getChildMenuList().add(m1_7);
        ml.add(m1);

//        再加入第二个二级菜单
        SysMenu m2 = new SysMenu("system", "业务管理", "layui-icon layui-icon-snowflake", false, null);
        SysMenu m2_1 = new SysMenu("system", "部门管理", "layui-icon layui-icon-water", true, null);
        SysMenu m2_2 = new SysMenu("system", "员工管理", "layui-icon layui-icon-user", true, null);
        m2.getChildMenuList().add(m2_1);
        m2.getChildMenuList().add(m2_2);
        ml.add(m2);
        return ml;
    }
}
