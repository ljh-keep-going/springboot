package com.baidu.supermarket.pojo;

import lombok.Data;

@Data
public class Cart {
    private Integer cid; //购物车主键
    private Integer pid; //商品主键
    private Integer pquantity; //商品数量
    private Integer uid; //用户主键

    //配置购物车对应的用户
    private User user;

    //配置购物车对应的商品
    private Product product;
}
