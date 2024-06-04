package com.baidu.supermarket.controller;

import com.baidu.supermarket.pojo.Role;
import com.baidu.supermarket.service.RoleService;
import com.baidu.supermarket.util.AjaxResult;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //根据主键删除角色
    @RequestMapping("/deleteRoleByRid/{rid}")
    @ResponseBody
    public AjaxResult deleteRoleByRid(@PathVariable("rid") Integer rid){
        System.out.println("执行根据id删除指定角色");
        System.out.println("rid:"+rid);

        //执行删除操作
        int total=roleService.deleteRoleByRid(rid);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }


    //执行修改角色功能
    @RequestMapping("/editRole")
    @ResponseBody
    public AjaxResult editRole(Role role){
        System.out.println("执行修改角色功能");
        System.out.println("role:"+role);

        //执行修改操作
        int total=roleService.updateRole(role);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //进入角色编辑页面
    @RequestMapping("toEditRole/{rid}")
    public String toEditRole(@PathVariable("rid")Integer rid, HttpServletRequest request){
        System.out.println("进入角色编辑页面");
        System.out.println("rid:"+rid);

        //根据id查询指定角色
        Role role=roleService.getRoleByRid(rid);
        request.setAttribute("role",role);

        return "role/edit";
    }

    //执行批量删除
    @RequestMapping("/batchRole")
    @ResponseBody
    public AjaxResult batchRole(@RequestParam("roleIds[]")Integer roleIds[]){
        System.out.println("执行批量删除");
        System.out.println("参数："+roleIds);
        for(int rid:roleIds){
            System.out.println("rid:"+rid);
        }

        //执行批量删除
        int total=roleService.batchDelRole(roleIds);
        if(total==roleIds.length){
            return AjaxResult.right();
        }else {
            return AjaxResult.error();
        }
    }

    //执行添加角色功能
    @RequestMapping("/addRole")
    @ResponseBody
    public AjaxResult addRole(Role role){
        System.out.println("执行添加角色");
        System.out.println("role:"+role);

        //执行添加角色功能
        int total=roleService.addRole(role);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }


    }

    //进入添加角色页面
    @RequestMapping("/toAddRole")
    public String toAddRole(){
        return "role/add";
    }

    //查询所有角色信息
    @RequestMapping("/roleList")
    @ResponseBody
    public PageResult<Role> getAllRolesByPage(
            @RequestParam(value = "pname",defaultValue = "") String pname,
            @RequestParam(value = "page",defaultValue = "1") Integer currentPage,
            @RequestParam(value="limit",defaultValue = "5") Integer pageSize){
        System.out.println("进入分页查询所有角色");
        System.out.println("pname:"+pname+" currentPage:"+currentPage+" pageSize:"+pageSize);

        //执行查询操作
        PageResult<Role> pageResult=roleService.getAllRolesByPage(pname,currentPage,pageSize);
        return pageResult;
    }

    //进入角色主页
    @RequestMapping("/toRoleList")
    public String toRoleList(){
        return "role/list";
    }
}
