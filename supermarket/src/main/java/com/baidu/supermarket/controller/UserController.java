package com.baidu.supermarket.controller;

import com.baidu.supermarket.pojo.Role;
import com.baidu.supermarket.pojo.User;
import com.baidu.supermarket.service.RoleService;
import com.baidu.supermarket.service.UserService;
import com.baidu.supermarket.util.AjaxResult;
import com.baidu.supermarket.util.MyDate;
import com.baidu.supermarket.util.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //批量删除
    @RequestMapping("/batchDelUser")
    @ResponseBody
    //注意：@RequestParam("userIds[]")必须要，不能不写，这是因为浏览器在发送数据的时候实际是以userIds[]作为变量名的
    public AjaxResult batchDelUser(@RequestParam("userIds[]") Integer userIds[]){
        System.out.println("执行批量删除");
        System.out.println("批量删除的参数为："+userIds);
        for(Integer id:userIds){
            System.out.println("id="+id);
        }
        //执行批量删除操作
        int total=userService.batchDelUser(userIds);
        if(total==userIds.length){
            return  AjaxResult.right();
        }else {
            return AjaxResult.error();
        }
//        return AjaxResult.right();
    }

    @RequestMapping("/userList")
    @ResponseBody
    public PageResult<User> userList(@RequestParam(value = "pname",defaultValue = "") String pname,
                                     @RequestParam(value = "page",defaultValue = "1") int currentPage,
                                     @RequestParam(value = "limit",defaultValue = "5") int pageSize){
        System.out.println("查询所有用户信息");
        System.out.println("pname:"+pname);
        System.out.println("currentPage:"+currentPage);
        System.out.println("pageSize:"+pageSize);


        PageResult<User> pageResult= userService.getAllUser(pname,currentPage,pageSize);
        System.out.println("pageResult1:"+pageResult);
        return pageResult;

    }

    @RequestMapping("/userList2")
    @ResponseBody
    public PageResult<User> userList2( HttpSession session) {
        System.out.println("-------userList2--------");
        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        //获取uid值
        Integer uid = user.getUid();
        //根据uid查询指定的用户信息
        User user2=userService.getUserById(uid);

        PageResult<User> pageResult=new PageResult<>();
        pageResult.setCode(0);
        pageResult.setMsg("查询成功");
        pageResult.setCount(1);

        List<User> list=new ArrayList<>();
        list.add(user2);
        pageResult.setData(list);

        System.out.println("pageResult2:"+pageResult);
        return pageResult;
    }





    //根据主键删除指定的用户信息
    @RequestMapping("/deleteUser/{uid}")
    @ResponseBody
    public AjaxResult deleteUser(@PathVariable Integer uid){
        System.out.println("删除指定的用户");
        System.out.println("uid:"+uid);

        //执行删除操作
        int total=userService.deleteUserByUid(uid);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //跳转到购买的用户登录页面
    @RequestMapping("/toUserLogin")
    public String toUserLogin(){

        System.out.println("-------toUserLogin--------");

        return "user/userLogin";
    }

    //购买时用户登录
    @RequestMapping("/userLogin")
    @ResponseBody
    public AjaxResult userLogin(User user,HttpSession session){
        System.out.println("-----购买时用户登录----");

        User user2=userService.userLogin(user);
        System.out.println("user2:"+user2);
        if(user2!=null){ //登录成功
            //将登录对象保存到session中
            session.setAttribute("user",user2);
            return AjaxResult.right();
        }else{//登录失败
            return AjaxResult.error();
        }
    }

    //用户后台登录
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(User user, HttpSession session){
        System.out.println("进入登录请求");
        System.out.println("登录参数："+user);
        User user2=userService.login(user);
        System.out.println("user2:"+user2);
        if(user2!=null){ //登录成功
            //将登录对象保存到session中
            session.setAttribute("user",user2);
            return AjaxResult.right();
        }else{//登录失败
            return AjaxResult.error();
        }

    }

    //管理员退出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //当前session失效
        session.invalidate();

        return "redirect:/user/toLogin";
    }

    //用户退出
    @RequestMapping("/logout2")
    public String logout2(HttpSession session){
        //当前session失效
        session.invalidate();

        return "redirect:/user/toLogin2";
    }


    //添加用户信息
    @RequestMapping("/addUser")
    @ResponseBody
    public AjaxResult addUser(User user,String myTime){
        System.out.println("执行添加用户操作");
        System.out.println("接收参数："+user);
        System.out.println("myTime:"+myTime);

        //将字符串转换成Date对象
        Date date= MyDate.stringToDate(myTime);

        //将时间封装到User对象
        user.setCreateTime(date);

        //执行添加操作
        int total=userService.addUser(user);
        if (total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //用户注册
    @RequestMapping("/userRegister")
    @ResponseBody
    public AjaxResult userRegister(User user){
        System.out.println("进入用户注册");
        System.out.println("user:"+user);

        //封装数据
        //封装创建时间
        user.setCreateTime(new Date());

        //封装用户角色为普通用户
        user.setRoleId(2);

        //执行添加操作
        int total=userService.addUser(user);

        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }
    }

    //进入添加页面
    @RequestMapping("/toAddUser")
    public String toAddUser(HttpServletRequest request){
        System.out.println("进入添加用户页面");
        //获取所有角色
        List<Role> list=roleService.getAllRoles();
        request.setAttribute("list",list);
        return "user/add";
    }

    //执行修改操作
    @RequestMapping("/editUser")
    @ResponseBody
    public AjaxResult editUser(User user,String myTime){
        System.out.println("执行修改操作");
        System.out.println("接收的参数："+user);
        System.out.println("myTime:"+myTime);

        //封装时间
        Date date=MyDate.stringToDate(myTime);
        user.setCreateTime(date);

        //执行修改操作
        int total=userService.updateUser(user);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //进入编辑页面，根据id查询指定的用户信息
    @RequestMapping("/toEditUser/{uid}")
    public String toEditUser(@PathVariable("uid") Integer uid, HttpServletRequest request){
        System.out.println("进入用户编辑页面");
        System.out.println("uid:"+uid);

        //根据id查询指定的用户信息
        User user=userService.getUserById(uid);
        request.setAttribute("user",user);

        //查询所有角色信息
        List<Role> list=roleService.getAllRoles();
        request.setAttribute("list",list);

        return "user/edit";
    }

    //进入编辑页面，根据id查询指定的用户信息
    @RequestMapping("/toEditUser2/{uid}")
    public String toEditUser2(@PathVariable("uid") Integer uid, HttpServletRequest request){
        System.out.println("进入个人信息修改页面");
        System.out.println("uid:"+uid);

        //根据id查询指定的用户信息
        User user=userService.getUserById(uid);
        request.setAttribute("user",user);

        //查询所有角色信息
        List<Role> list=roleService.getAllRoles();
        request.setAttribute("list",list);

        return "user/edit2";
    }

    //用户实现支付功能
    @RequestMapping("/toPay")
    public String toPay(){
        return "location/pay";
    }

    @RequestMapping("/pay2")
    public String pay02(){
        return "location/pay2";
    }

    @RequestMapping("/pay3")
    public String pay03(){
        return "location/pay3";
    }


    //进入用户管理页面
    @RequestMapping("/toUserList")
    public String toUserList(){
        return "user/list";
    }

    //进入用户信息页面
    @RequestMapping("/toUserList2")
    public String toUserList2(){
        return "user/list2";
    }


    //进入管理员login页面
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }

    //进入用户login页面
    @RequestMapping("/toLogin2")
    public String toLogin2(){
        return "user/userLogin";
    }

    //进入用户注册页面
    @RequestMapping("/toResgister")
    public String toResgister(){
        return "user/reg";
    }

    //进入忘记密码页面
    @RequestMapping("/toForget")
    public String toForget(){
        return "user/forget";
    }
}
