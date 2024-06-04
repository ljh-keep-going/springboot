package com.baidu.supermarket;

import com.baidu.supermarket.mapper.TOrderMapper;
import com.baidu.supermarket.pojo.TOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestOrderMapper {

    @Autowired
    private TOrderMapper orderMapper;

    @Test
    public void testAddOrder(){
        TOrder order=new TOrder();
        order.setUid(1);
        order.setCreatetime(new Date());
        order.setTotalPrice(100000.123d);
        order.setStatus("未支付");
        int total=orderMapper.addOrder(order) ;
        System.out.println("total:"+total);
        System.out.println("order:"+order);
    }
}
