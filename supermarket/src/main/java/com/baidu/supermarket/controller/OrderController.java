package com.baidu.supermarket.controller;

import com.baidu.supermarket.pojo.TOrder;
import com.baidu.supermarket.pojo.User;
import com.baidu.supermarket.service.TOrderService;
import com.baidu.supermarket.util.AjaxResult;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private TOrderService orderService;

    //添加订单
    @RequestMapping("/addOrder")
    @ResponseBody
    public AjaxResult addOrder(Double totalPrice, @RequestParam("cids[]") Integer cids[], HttpSession session){
        System.out.println("进入添加订单的操作：totalPrice:"+totalPrice);
        System.out.println("购物车主键的数组，cids:"+cids);
        for(Integer cid:cids){
            System.out.println("cid:"+cid);
        }

        //获取当前登录的用户主键
        User user=(User)session.getAttribute("user");
        int uid=user.getUid();

        //封装订单信息
        TOrder order=new TOrder();
        order.setStatus("未支付");
        order.setTotalPrice(totalPrice);
        order.setCreatetime(new Date());
        order.setGoodsStatus("未发货");
        order.setUid(uid);


       //执行支付功能：添加订单，添加订单详情，删除购物车中已经添加到订单的购物车信息
        //该方法返回值是添加的订单信息
        TOrder order2=orderService.buy(order,cids);

        //将添加的订单信息保存到session中
        session.setAttribute("order",order2);

        if(order2!=null){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }
    }

    //跳转到查询所有订单的页面
    @RequestMapping("/toOrderList")
    public String toOrderList(){
        return "order/list";
    }

    //跳转到查询用户订单的页面
    @RequestMapping("/toOrderList2")
    public String toOrderList2(){
        return "order/list2";
    }

    //查询所有订单信息
    @RequestMapping("/orderList")
    @ResponseBody
    public PageResult<TOrder> getAllOrders(@RequestParam(value = "page",defaultValue = "1")Integer currentPage,
                                           @RequestParam(value = "limit",defaultValue = "5")Integer pageSize){
        System.out.println("查询所有订单信息：");
        System.out.println("currentPage:"+currentPage);
        System.out.println("pageSize:"+pageSize);

        //执行查询操作
        PageResult<TOrder> pageResult=orderService.getAllOrders(currentPage,pageSize);

        return pageResult;

    }

    //根据uid查询指定的用户订单信息
    @RequestMapping("/orderList2")
    @ResponseBody
    public PageResult<TOrder> getAllOrders2(HttpSession session,

                                            @RequestParam(value = "page",defaultValue = "1")Integer currentPage,
                                           @RequestParam(value = "limit",defaultValue = "5")Integer pageSize){
        System.out.println("查询用户的订单信息：");
        System.out.println("currentPage:"+currentPage);
        System.out.println("pageSize:"+pageSize);

        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        //获取uid值
        Integer uid = user.getUid();

        //执行查询操作
        PageResult<TOrder> pageResult=orderService.getAllOrdersByUid(uid,currentPage,pageSize);

        System.out.println("用户订单pageResult:"+pageResult);

        return pageResult;

    }

    //批量删除
    @RequestMapping("/batchDelOrder")
    @ResponseBody
    public AjaxResult batchDelOrder(@RequestParam("orderIds[]")Integer orderIds[]){
        System.out.println("执行批量删除，orderIds："+orderIds);
        for(Integer oid:orderIds){
            System.out.println("oid:"+oid);
        }

        //执行批量删除
        int total=orderService.batchDelOrder(orderIds);
        if(total==orderIds.length){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //根据主键删除指定的订单
    @RequestMapping("/deleteOrderByOid/{oid}")
    @ResponseBody
    public AjaxResult deleteOrderByOid(@PathVariable("oid")Integer oid){
        System.out.println("执行单个的删除，oid:"+oid);

        //执行单个删除
        int total=orderService.deleteOrderByOid(oid);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }
    }

    //发货处理
    @RequestMapping("/editOrder/{oid}")
    @ResponseBody
    public AjaxResult editOrder(@PathVariable("oid")Integer oid){
        System.out.println("发货处理，oid:"+oid);

        //修改订单状态为已经发货
        int total=orderService.updateOrder(oid);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }
    }
}
