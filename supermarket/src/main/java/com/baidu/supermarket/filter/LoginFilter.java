package com.baidu.supermarket.filter;

import com.baidu.supermarket.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "loginFilter",urlPatterns = {"/cart/showcartList","/toUserIndex","/cart/addCart"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //用户登录验证
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //向下转型
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        //获取HttpSession对象
        HttpSession session=request.getSession();

        //获取登录的用户对象
        User user=(User) session.getAttribute("user");
        if(user!=null){
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect("/user/toUserLogin");
        }
    }

    @Override
    public void destroy() {

    }
}
