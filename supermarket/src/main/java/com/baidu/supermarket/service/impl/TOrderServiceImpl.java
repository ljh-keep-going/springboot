package com.baidu.supermarket.service.impl;

import com.baidu.supermarket.mapper.CartMapper;
import com.baidu.supermarket.mapper.OrderDetailMapper;
import com.baidu.supermarket.mapper.TOrderMapper;
import com.baidu.supermarket.pojo.Cart;
import com.baidu.supermarket.pojo.OrderDetail;
import com.baidu.supermarket.pojo.TOrder;
import com.baidu.supermarket.service.TOrderService;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TOrderServiceImpl implements TOrderService {

    @Autowired(required = false)
    private TOrderMapper orderMapper;

    @Autowired(required = false)
    private CartMapper cartMapper;

    @Autowired(required = false)
    private OrderDetailMapper detailMapper;


    //执行支付功能：添加订单，添加订单详情，删除购物车中已经添加到订单的购物车信息
    //该方法返回值是添加的订单信息
    public TOrder buy(TOrder tOrder, Integer cids[]){
        //添加订单信息，并返回订单主键
      int sumAddOrder= orderMapper.addOrder(tOrder);

        //根据购物车主键数组查询购物车信息
       List<Cart> list=cartMapper.getCartByCids(cids);

       int sumAddDetail=0;//保存添加订单详情成功的总次数
       for(Cart cart:list){
           //获取商品主键
           int pid=cart.getPid();

           //获取订单主键
           int oid=tOrder.getOid();

           //封装订单详情数据
           OrderDetail detail=new OrderDetail();
           detail.setOid(oid);
           detail.setPid(pid);

           //添加订单详情
           int res=detailMapper.addOrderDetail(detail);

           //保存添加订单详情成功的总次数
           sumAddDetail=sumAddDetail+res;
       }

       //根据购物车主键数组删除购物车列表
        cartMapper.batchDelCart(cids);


       //添加订单与订单详情成功
       if((sumAddOrder==1)&&(sumAddDetail==cids.length)){
           return tOrder;
       }else{
           return null;
       }

    }

    //根据主键修改订单状态为已支付
    public int upateOrderByOid(Integer oid){
        return orderMapper.upateOrderByOid(oid);
    }

    //查询所有订单以及订单所在客户信息
    public PageResult<TOrder> getAllOrders(Integer currentPage, Integer pageSize){
        //计算起始位置
        int start=(currentPage-1)*pageSize;
        //执行查询
        List<TOrder> list=orderMapper.getAllOrders(start,pageSize);

        //查询总记录数
        int count=orderMapper.count();

        //封装结果
        PageResult<TOrder> pageResult=new PageResult<>() ;
        pageResult.setCode(0);
        pageResult.setMsg("操作成功");
        pageResult.setCount(count);
        pageResult.setData(list);

        return pageResult;

    }

    //根据uid查询指定的订单以及订单所在客户信息和商品名称
    public PageResult<TOrder> getAllOrdersByUid(Integer uid, Integer currentPage, Integer pageSize){
        //计算起始位置
        int start=(currentPage-1)*pageSize;

        //执行查询
        List<TOrder> list=orderMapper.getAllOrdersByUid(uid,start,pageSize);

        //查询总记录数
        int count=orderMapper.count2(uid);

        //封装结果
        PageResult<TOrder> pageResult=new PageResult<>() ;
        pageResult.setCode(0);
        pageResult.setMsg("操作成功");
        pageResult.setCount(count);
        pageResult.setData(list);

        return pageResult;

    }

    //执行批量删除
    public int batchDelOrder(Integer oids[]){
        //批量删除订单
        int total= orderMapper.batchDelOrder(oids);

        //批量删除订单详情
        int sum=detailMapper.batchDelDetailOrder(oids);

        if(total==oids.length&&sum>0){
            return total;
        }else{
            return 0;
        }
    }

    //根据主键删除指定的订单
    public int deleteOrderByOid(Integer oid){
        //删除订单
        int total=orderMapper.deleteOrderByOid(oid);
        //删除该订单关联的订单详情
       int sum= detailMapper.deleteDetailOrderByOid(oid);

       if(total>0&&sum>0){
           return 1;
       }else{
           return 0;
       }
    }

    //修改订单状态为已经发货
    public int updateOrder(Integer oid){
        return orderMapper.updateOrder(oid);
    }
}
