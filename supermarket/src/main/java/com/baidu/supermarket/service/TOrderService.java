package com.baidu.supermarket.service;

import com.baidu.supermarket.pojo.TOrder;
import com.baidu.supermarket.util.PageResult;

public interface TOrderService {

    //执行支付功能：添加订单，添加订单详情，删除购物车中已经添加到订单的购物车信息
    public TOrder buy(TOrder tOrder,Integer cids[]);

    //根据主键修改订单状态为已支付
    public int upateOrderByOid(Integer oid);

    //查询所有订单以及订单所在客户信息
    public PageResult<TOrder> getAllOrders(Integer currentPage, Integer pageSize);

    //根据uid查询指定的订单以及订单所在客户信息和商品名称
    public PageResult<TOrder> getAllOrdersByUid(Integer uid,Integer start,Integer pageSize);

    //执行批量删除
    public int batchDelOrder(Integer oids[]);

    //根据主键删除指定的订单
    public int deleteOrderByOid(Integer oid);

    //修改订单状态为已经发货
    public int updateOrder(Integer oid);
}
