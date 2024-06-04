package com.baidu.springboot07.filter;

import com.baidu.springboot07.pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "loginFilter",urlPatterns =  {"/toIndex","/cart/addCart","/buy"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    //登录验证
    // ServletRequest是HttpServletRequest的父类
    // ServletResponse是HttpServletResponse的父类
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("---------登录验证----------");

        //向下转型，将父类对象转换成子类对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取HttpSession对象
        HttpSession session = request.getSession();

        //从session中获取当前登录的用户对象
        User user = (User) session.getAttribute("user2");
        System.out.println("user:" + user);

        if (user!=null) { //用户已经登录
            //请求向下传递
            filterChain.doFilter(request, response);
        } else { //用户还没有登录
            //重定向到登录页面
            response.sendRedirect("/user/toUserLogin");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
