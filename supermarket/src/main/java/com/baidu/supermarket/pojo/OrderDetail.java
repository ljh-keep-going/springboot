package com.baidu.supermarket.pojo;

import lombok.Data;

@Data
public class OrderDetail {
    private Integer opid; //订单详情表主键
    private Integer oid; //关联的订单主键
    private Integer pid; //关联的商品主键

    //配置关联的商品信息
    private Product product;

    //配置订单详情对应的订单信息
    private TOrder tOrder;
}
