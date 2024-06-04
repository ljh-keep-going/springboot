package com.baidu.supermarket.controller;

import com.baidu.supermarket.util.SysMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {



//    跳转到管理员登录成功的后台首页
    @RequestMapping("/toIndex")
    public String toContent(HttpServletRequest request) {
        //保存左侧功能栏
        request.setAttribute("userMenuList",getSysMenu());
        return "content";
    }

    //跳转到普通用户登录成功的后台首页
    @RequestMapping("/toUserIndex")
    public String toUserContent(HttpServletRequest request) {
        //保存左侧功能栏
        request.setAttribute("userMenuList",getUserMenu());
        return "userMenu/userContent";
    }


    //返回欢迎页面
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    //管理员的菜单
    private List<SysMenu> getSysMenu() {
        List<SysMenu> ml = new ArrayList<SysMenu>();
        SysMenu m1 = new SysMenu("system", "系统管理", "layui-icon layui-icon-util", false, null);
        SysMenu m1_1 = new SysMenu("system", "用户管理", "layui-icon layui-icon-set ", true, "/user/toUserList");
        SysMenu m1_2 = new SysMenu("system", "角色管理", "layui-icon layui-icon-set ", true, "/role/toRoleList");
        SysMenu m1_3 = new SysMenu("system", "商品管理", "layui-icon layui-icon-set", true, "/prod/toList");
        SysMenu m1_4 = new SysMenu("system", "购物车管理", "layui-icon layui-icon-set ", true, "/cart/toCartList");
        SysMenu m1_5 = new SysMenu("system", "订单管理", "layui-icon layui-icon-set ", true, "/order/toOrderList");
        SysMenu m1_6 = new SysMenu("system", "客户地址管理", "layui-icon layui-icon-set ", true, "/location/toLocationList");

//        SysMenu m1_7 = new SysMenu("system", "订单月度分析", "layui-icon layui-icon-set", false, "/prod/toView1");
//        SysMenu m1_8 = new SysMenu("system", "订单占比分析", "layui-icon layui-icon-set", false, "/prod/toView2");
        m1.getChildMenuList().add(m1_1);
        m1.getChildMenuList().add(m1_2);
        m1.getChildMenuList().add(m1_3);
        m1.getChildMenuList().add(m1_4);
        m1.getChildMenuList().add(m1_5);
        m1.getChildMenuList().add(m1_6);
//        m1.getChildMenuList().add(m1_7);
//        m1.getChildMenuList().add(m1_8);
        ml.add(m1);

//        再加入第二个二级菜单
//        SysMenu m2 = new SysMenu("system", "业务管理", "layui-icon layui-icon-snowflake", false, null);
//        SysMenu m2_1 = new SysMenu("system", "部门管理", "layui-icon layui-icon-water", false, null);
//        SysMenu m2_2 = new SysMenu("system", "员工管理", "layui-icon layui-icon-user", false, null);
//        m2.getChildMenuList().add(m2_1);
//        m2.getChildMenuList().add(m2_2);
//        ml.add(m2);
        return ml;
    }

    //用户的菜单
    private List<SysMenu> getUserMenu() {
        List<SysMenu> ml = new ArrayList<SysMenu>();
        SysMenu m1 = new SysMenu("system", "个人中心", "layui-icon layui-icon-util", false, null);
        SysMenu m1_1 = new SysMenu("system", "我的信息", "layui-icon layui-icon-set ", true, "/user/toUserList2");
        SysMenu m1_2 = new SysMenu("system", "我的购物车", "layui-icon layui-icon-set ", true, "/cart/toCartList2");
        SysMenu m1_3 = new SysMenu("system", "我的订单", "layui-icon layui-icon-set ", true, "/order/toOrderList2");
        SysMenu m1_4 = new SysMenu("system", "我的地址", "layui-icon layui-icon-set ", true, "/location/toLocationList2");

        m1.getChildMenuList().add(m1_1);
        m1.getChildMenuList().add(m1_2);
        m1.getChildMenuList().add(m1_3);
        m1.getChildMenuList().add(m1_4);
        ml.add(m1);

        return ml;
    }
}
