package com.baidu.supermarket.controller;

import com.baidu.supermarket.pojo.Cart;
import com.baidu.supermarket.pojo.User;
import com.baidu.supermarket.service.CartService;
import com.baidu.supermarket.util.AjaxResult;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    //根据id删除指定的购物车信息
    @RequestMapping("/deleteCartByCid/{cid}")
    @ResponseBody
    public AjaxResult deleteCartByCid(@PathVariable("cid") Integer cid){
        System.out.println("执行根据id删除指定的购物车信息");
        System.out.println("cid:"+cid);

        //执行删除操作
        int total=cartService.deleteCartByCid(cid);
        if(total==1){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //批量删除购物车
    @RequestMapping("/batchDelCart")
    @ResponseBody
    public AjaxResult batchDelCart(@RequestParam("cids[]") Integer cids[]){
        System.out.println("执行批量删除购物车");
        System.out.println("参数："+cids);
        for(Integer cid:cids){
            System.out.println("cid:"+cid);
        }

        //执行批量删除
        int total=cartService.batchDelCart(cids);
        if(total==cids.length){
            return AjaxResult.right();
        }else{
            return AjaxResult.error();
        }

    }

    //查询所有购物车列表
    @RequestMapping("/cartList")
    @ResponseBody
    public PageResult<Cart> cartList(
            @RequestParam(value = "pname",defaultValue = "") String pname,
            @RequestParam(value = "page",defaultValue = "1") Integer currentPage,
            @RequestParam(value = "limit",defaultValue = "5") Integer pageSize){
        System.out.println("查询所有购物车信息");
        System.out.println("pname:"+pname+" currentPage:"+currentPage+" pageSize:"+pageSize);

        //执行查询操作
        PageResult<Cart> pageResult=cartService.getAllCart(pname,currentPage,pageSize);

        return pageResult;
    }

    //查询用户购物车列表
    @RequestMapping("/cartList2")
    @ResponseBody
    public PageResult<Cart> cartList2(HttpSession session,
                                      @RequestParam(value = "page",defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "limit",defaultValue = "5") Integer pageSize){
        System.out.println("------cartList2-------");
        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        //获取uid值
        Integer uid2 = user.getUid();
        //执行查询操作

        //根据uid查询对应的用户购物车信息以及用户信息，商品信息
        PageResult<Cart> pageResult=cartService.getAllCartById(uid2,currentPage,pageSize);

        System.out.println("pageResult2:"+pageResult);
        return pageResult;
    }

    //跳转到到购物车列表
    @RequestMapping("/toCartList")
    public String toCartList(){
        return "cart/list";
    }


    //跳转到到用户的购物车列表
    @RequestMapping("/toCartList2")
    public String toCartList2(){
        return "cart/list2";
    }

    //添加商品到购物车
    @RequestMapping("/addCart")
    public String addCart(Integer pid, HttpSession session){
        System.out.println("添加商品到购物车：pid="+pid);

        Cart cart=new Cart();
        //设置商品主键
        cart.setPid(pid);
        //设置商品数量
        cart.setPquantity(1);

        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        //设置用户主键
        int uid=user.getUid();
        cart.setUid(uid);

        //添加商品到购物车
        int total=cartService.addCart(cart);


        return "redirect:/cart/showcartList";
//        return "redirect:/cart/toCartList";
    }
    //添加到购物车以后查看购物车
    @RequestMapping("/showcartList")
    public String showcartList(){
        return "cart/showcartList";
    }

    //计算勾选商品的总金额
    @RequestMapping("/countMoney")
    @ResponseBody
    public String countMoney(@RequestParam("cids[]") Integer ids[]){
        System.out.println("计算总金额，ids="+ids);
       if(ids.length>0){
          for(Integer id:ids){
              System.out.println("id:"+id);
          }
       }

       //根据购物车主键的数组计算总金额
        double totalMoney=0.0;
        List<Cart> list=cartService.getCartByCids(ids);
        for(Cart cart:list){
            totalMoney=totalMoney+cart.getProduct().getProdPrice()*cart.getPquantity();
        }

        System.out.println("总金额是：totalMoney:"+totalMoney);

        return totalMoney+"";
    }
}
