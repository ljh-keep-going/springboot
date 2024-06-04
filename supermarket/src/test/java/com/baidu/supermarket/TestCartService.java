package com.baidu.supermarket;

import com.baidu.supermarket.pojo.Cart;
import com.baidu.supermarket.service.CartService;
import com.baidu.supermarket.util.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestCartService {
    @Autowired
    private CartService cartService;

    @Test
    public void testGetAllCart(){
        PageResult<Cart> result=cartService.getAllCart("",1,5);
        System.out.println(result);
    }

    //测试根据id查询购物车以及商品信息
    @Test
    public void testCartByCids(){
        Integer ids[]={1,2,3};
        List<Cart> list=cartService.getCartByCids(ids);
        for(Cart cart:list){
            System.out.println("cart:"+cart);
        }
    }
}
