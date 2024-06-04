package com.baidu.supermarket.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class TOrder {
    private Integer oid; //订单主键
    private Double totalPrice; //商品总价
    private Integer uid; //用户主键
    private Date createtime; //订单创建时间
    private String status; //支付状态
    private String goodsStatus; //发货与收货状态

    private User user;

    private OrderDetail orderDetail;
    private Product product;
}
