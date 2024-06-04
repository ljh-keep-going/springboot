package com.baidu.supermarket.service.impl;

import com.baidu.supermarket.mapper.CartMapper;
import com.baidu.supermarket.pojo.Cart;
import com.baidu.supermarket.service.CartService;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired(required = false)
    private CartMapper cartMapper;

    //查询所有用户的购物车信息以及管理的用户信息，商品信息
    public PageResult<Cart> getAllCart(String pname,Integer currentPage,Integer pageSize){
        //查询所有购物车信息
        int start=(currentPage-1)*pageSize;
        List<Cart> list=cartMapper.getAllCart(pname,start,pageSize);

        //查询总记录数
        int count=cartMapper.count(pname);

        //封装结果
        PageResult<Cart> pageResult=new PageResult<>();

        pageResult.setCode(0);
        pageResult.setMsg("查询成功");
        pageResult.setCount(count);
        pageResult.setData(list);
        return pageResult;
    }

    //根据uid查询对应的用户购物车信息以及用户信息，商品信息
    public PageResult<Cart> getAllCartById(Integer uid,Integer currentPage,Integer pageSize){
        //查询所有购物车信息
        int start=(currentPage-1)*pageSize;
        List<Cart> list=cartMapper.getAllCartById(uid,start,pageSize);

        //查询uid总记录数
        int count=cartMapper.count2(uid);

        //封装结果
        PageResult<Cart> pageResult=new PageResult<>();

        pageResult.setCode(0);
        pageResult.setMsg("查询成功");
        pageResult.setCount(count);
        pageResult.setData(list);
        return pageResult;
    }

    //批量删除购物车
    public int batchDelCart(Integer cids[]){
        return cartMapper.batchDelCart(cids);
    }


    //根据主键删除购物车信息
    public int deleteCartByCid(Integer cid){
        return cartMapper.deleteCartByCid(cid);
    }

    //添加商品到购物车
    public int addCart(Cart cart){
        //根据pid与uid查询购物车表中是否存在该商品
        Cart cart2=cartMapper.getCartByUidAndPid(cart);

        if(cart2!=null){ //说明商品已经存在，则只需要修改商品数量即可
            //设置新的商品数量
            cart.setPquantity(cart2.getPquantity()+cart.getPquantity());

            //执行修改操作
           return cartMapper.upateCartByUidAndPid(cart);
        }else{//插入记录，添加新的商品
            return cartMapper.addCart(cart);
        }
    }

    //查询被勾选的购物车以及对应的商品信息
    public List<Cart> getCartByCids(Integer cids[]){
        return cartMapper.getCartByCids(cids);
    }
}
