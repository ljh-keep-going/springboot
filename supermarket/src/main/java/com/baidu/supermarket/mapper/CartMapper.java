package com.baidu.supermarket.mapper;

import com.baidu.supermarket.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    //查询所有用户的购物车信息以及管理的用户信息，商品信息
    public List<Cart> getAllCart(@Param("pname") String pname, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    //根据uid查询对应的用户购物车信息以及用户信息，商品信息
    public List<Cart> getAllCartById( @Param("uid") Integer uid,@Param("start") Integer start, @Param("pageSize")Integer pageSize);

    //根据pname查询总记录数
    public int count(String pname);

    //根据uid查询总记录数
    public int count2(Integer uid);

    //批量删除购物车
    public int batchDelCart(Integer cids[]);

    //根据主键删除购物车信息
    @Delete("delete from cart where cid=#{cid}")
    public int deleteCartByCid(Integer cid);


    //根据pid与uid查询购物车表中是否存在该商品
    @Select("select* from cart where uid=#{uid} and pid=#{pid}")
    public Cart getCartByUidAndPid(Cart cart);

    //根据uid与pid修改商品数量
    @Update("update cart set pquantity=#{pquantity} where uid=#{uid} and pid=#{pid}")
    public int upateCartByUidAndPid(Cart cart);

    //添加商品到购物车
    @Insert("insert into cart(pid,pquantity,uid) values(#{pid},#{pquantity},#{uid})")
    public int addCart(Cart cart);

    //查询被勾选的购物车以及对应的商品信息
    public List<Cart> getCartByCids(Integer cids[]);
}
