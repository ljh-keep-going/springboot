package com.baidu.supermarket.service;

import com.baidu.supermarket.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    //根据订单主键查询订单详情
    public List<OrderDetail> getOrderDetailByOid(int oid);
}
