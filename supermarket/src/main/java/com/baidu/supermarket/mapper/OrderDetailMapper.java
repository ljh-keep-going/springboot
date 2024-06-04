package com.baidu.supermarket.mapper;

import com.baidu.supermarket.pojo.OrderDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    //添加订单详情
    @Insert("insert into t_order_detail(oid,pid) values(#{oid},#{pid})")
    public int addOrderDetail(OrderDetail detail);

    //根据订单主键查询订单详情
    public List<OrderDetail> getOrderDetailByOid(int oid);

    //批量删除订单详情
    public int batchDelDetailOrder(Integer oids[]);

    //根据oid删除订单详情
    @Delete("delete from t_order_detail where oid=#{oid}")
    public int deleteDetailOrderByOid(Integer oid);
}
