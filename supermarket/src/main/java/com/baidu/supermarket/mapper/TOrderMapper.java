package com.baidu.supermarket.mapper;

import com.baidu.supermarket.pojo.TOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TOrderMapper {

    //添加订单信息，并i返回订单主键
    @Insert("insert into t_order(status,total_price,uid,createtime,goodsStatus)" +
            " values(#{status},#{totalPrice},#{uid},#{createtime},#{goodsStatus})")
    @Options(useGeneratedKeys = true,keyColumn = "oid",keyProperty = "oid")
    public int addOrder(TOrder torder);

    //根据主键修改订单状态为已支付
    @Update("update t_order set status='已支付' where oid=#{oid}")
    public int upateOrderByOid(Integer oid);


    //查询所有订单以及订单所在客户信息
    public List<TOrder> getAllOrders(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    //根据uid查询指定的订单以及订单所在客户信息和商品名称
    public List<TOrder> getAllOrdersByUid(@Param("uid") Integer uid,@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    //查询总记录数
    public int count();

    //查询uid总记录数
    public int count2(Integer uid);

    //执行批量删除
    public int batchDelOrder(Integer oids[]);

    //根据主键删除指定的订单
    @Delete("delete from t_order where oid=#{oid}")
    public int deleteOrderByOid(Integer oid);

    //修改订单状态为已经发货
    @Update("update t_order set goodsStatus='已发货' where oid=#{oid}")
    public int updateOrder(Integer oid);
}
