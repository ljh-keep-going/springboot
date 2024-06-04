package com.baidu.supermarket.filter;

import com.baidu.supermarket.pojo.Role;
import com.baidu.supermarket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName  = "roleFilter",value = "/user/toLogin")
public class RoleFilter implements Filter {

    @Autowired
    private RoleService roleService;

    //查询所有角色，并注入到login.html页面中
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("-------RoleFilter-----doFilter--");
        System.out.println("roleService:"+roleService);

        //查询所有角色
        List<Role> list=roleService.getAllRoles();
        for(Role role:list){
            System.out.println("role:"+role);
        }

        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        request.setAttribute("list",list);

        filterChain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("-----启动过滤器------");
    }

    @Override
    public void destroy() {
        System.out.println("------销毁过滤器------");
    }
}
