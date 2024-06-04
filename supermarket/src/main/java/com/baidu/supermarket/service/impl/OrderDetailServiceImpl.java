package com.baidu.supermarket.service.impl;

import com.baidu.supermarket.mapper.OrderDetailMapper;
import com.baidu.supermarket.pojo.OrderDetail;
import com.baidu.supermarket.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired(required = false)
    private OrderDetailMapper detailMapper;

    //根据订单主键查询订单详情
    public List<OrderDetail> getOrderDetailByOid(int oid){
        return detailMapper.getOrderDetailByOid(oid);
    }
}
