package com.baidu.supermarket.controller;

import com.baidu.supermarket.pojo.*;
import com.baidu.supermarket.service.LocationService;
import com.baidu.supermarket.service.OrderDetailService;
import com.baidu.supermarket.service.TOrderService;
import com.baidu.supermarket.util.AjaxResult;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private OrderDetailService detailService;

    @Autowired
    private TOrderService orderService;

    @RequestMapping("/addAddress")
    public String addAddress(Location location, HttpServletRequest request,HttpSession session){
        System.out.println("----------addAddress----------");
        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        //获取uid值
        Integer uid = user.getUid();
        location.setUid(uid);
        //添加收货地址
        locationService.addAddress(location);
        System.out.println("添加收货地址，location:"+location);
        //根据订单主键查询订单详情
        int oid=location.getOid();
        List<OrderDetail> list=detailService.getOrderDetailByOid(oid);
        request.setAttribute("list",list);

        //根据订单主键查询收货地址信息
        Location myAddr=locationService.getAddressByOid(oid);
        request.setAttribute("location",myAddr);

        return "location/pay2";
    }

    //最后的支付
    @RequestMapping("/lastPay")
    public String lastPay(Integer oid,HttpSession session){
        System.out.println("最后的支付");
        Integer resOid=null;
        if(oid==null){//如果请求没有发送oid,则到session中查抄oid
            //修改订单信息，将当前订单的支付状态改为已支付
            TOrder order=(TOrder)session.getAttribute("order");
            resOid=order.getOid();
        }


        //修改订单状态为已支付
        int total=orderService.upateOrderByOid(resOid);

        return "redirect:/toUserIndex";
    }

    //进入所有用户地址页面
    @RequestMapping("/toLocationList")
    public String toLocationList(){
        return "location/list";
    }

    //查询所有的用户地址信息
    @RequestMapping("/locationList")
    @ResponseBody
    public PageResult<Location> locationList(@RequestParam(value="realName",defaultValue="") String realName,
                                              @RequestParam(value = "page",defaultValue = "1") Integer currentPage,
                                              @RequestParam(value = "limit",defaultValue = "5") Integer pageSize){
        System.out.println("------locationList-------");

        //根据uid查询对应的用户购物车信息以及用户信息，商品信息
        PageResult<Location> pageResult=locationService.getAlllocation(realName,currentPage,pageSize);

        System.out.println("pageResult2:"+pageResult);
        return pageResult;
    }


    //进入用户地址页面
    @RequestMapping("/toLocationList2")
    public String toLocationList2(){
        return "location/list2";
    }

    //根据uid查询对应的用户地址信息
    @RequestMapping("/locationList2")
    @ResponseBody
    public PageResult<Location> locationList2(HttpSession session,
                                      @RequestParam(value = "page",defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "limit",defaultValue = "5") Integer pageSize){
        System.out.println("------locationList2-------");
        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        //获取uid值
        Integer uid2 = user.getUid();
        //执行查询操作

        //根据uid查询对应的用户购物车信息以及用户信息，商品信息
        PageResult<Location> pageResult=locationService.getAlllocationByUid(uid2,currentPage,pageSize);

        System.out.println("pageResult2:"+pageResult);
        return pageResult;
    }


    //进入管理员添加地址页面
    @RequestMapping("/toAddLocation")
    public String toAddLocation(){
        System.out.println("进入添加地址页面");
        return "location/add";
    }

    //管理员添加用户地址信息
    @RequestMapping("/addLocation")
    @ResponseBody
    public AjaxResult addLocation(Location location,Integer aid){
        System.out.println("执行添加用户地址操作");

        //设置地址主键
        location.setAid(aid);

        System.out.println("接收地址参数："+location);
        //执行添加操作
        int total=locationService.addLocation(location);
        if (total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //进入用户添加地址页面
    @RequestMapping("/toAddLocation2")
    public String toAddUser2(){
        System.out.println("进入添加地址页面");
        return "location/add2";
    }

    //添加用户地址信息
    @RequestMapping("/addLocation2")
    @ResponseBody
    public AjaxResult addLocation2(Location location,Integer aid, HttpSession session){
        System.out.println("执行添加用户地址操作");

        //设置地址主键
        location.setAid(aid);

        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        //设置用户主键
        int uid=user.getUid();
        location.setUid(uid);

        System.out.println("接收地址参数："+location);
        //执行添加操作
        int total=locationService.addLocation(location);
        if (total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }



    //进入修改地址页面
//    @RequestMapping("/toEditLocation")
//    public String toEditLocation(){
//        System.out.println("进入添加地址页面");
//        return "location/edit";
//    }


    //执行修改操作
    @RequestMapping("/editLocation")
    @ResponseBody
    public AjaxResult editLocation(Location location){
        System.out.println("执行修改操作");
        System.out.println("接收的参数："+location);

        //执行修改操作
        int total=locationService.updateLocation(location);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //进入编辑页面，根据aid查询指定的用户地址信息
    @RequestMapping("/toEditLocation/{aid}")
    public String toEditUser(@PathVariable("aid") Integer aid, HttpServletRequest request){
        System.out.println("进入用户编辑页面");
        System.out.println("aid:"+aid);

        //根据aid查询指定的用户地址信息
        Location location=locationService.getAddressByAid(aid);
        request.setAttribute("location",location);

        System.out.println("发送参数location:"+location);
        return "location/edit";
    }


    //批量删除
    @RequestMapping("/batchDelLocation")
    @ResponseBody
    //注意：@RequestParam("userIds[]")必须要，不能不写，这是因为浏览器在发送数据的时候实际是以userIds[]作为变量名的
    public AjaxResult batchDelLocation(@RequestParam("locationIds[]") Integer locationIds[]){
        System.out.println("执行批量删除");
        System.out.println("批量删除的参数为："+locationIds);
        for(Integer id:locationIds){
            System.out.println("id="+id);
        }
        //执行批量删除操作
        int total= locationService.batchDelLocation(locationIds);
        if(total==locationIds.length){
            return  AjaxResult.right();
        }else {
            return AjaxResult.error();
        }
//        return AjaxResult.right();
    }

    //根据主键aid删除指定的用户地址信息
    @RequestMapping("/deleteLocation/{aid}")
    @ResponseBody
    public AjaxResult deleteLocation(@PathVariable Integer aid){
        System.out.println("删除指定的用户");
        System.out.println("aid:"+aid);

        //执行删除操作
        int total=locationService.deleteLocationByAid(aid);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }
}
