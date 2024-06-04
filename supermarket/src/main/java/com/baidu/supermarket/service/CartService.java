package com.baidu.supermarket.service;

import com.baidu.supermarket.pojo.Cart;
import com.baidu.supermarket.util.PageResult;

import java.util.List;

public interface CartService {
    //查询所有用户的购物车信息以及管理的用户信息，商品信息
    public PageResult<Cart> getAllCart(String pname,Integer currentPage,Integer pageSize);

    //根据id查询对应的用户购物车信息以及用户信息，商品信息
    public PageResult<Cart> getAllCartById(Integer uid,Integer currentPage,Integer pageSize);

    //根据uid查询总记录数
//    public int count2(Integer uid);

    //批量删除购物车
    public int batchDelCart(Integer cids[]);

    //根据主键删除购物车信息
    public int deleteCartByCid(Integer cid);

    //添加商品到购物车
    public int addCart(Cart cart);

    //查询被勾选的购物车以及对应的商品信息
    public List<Cart> getCartByCids(Integer cids[]);
}
