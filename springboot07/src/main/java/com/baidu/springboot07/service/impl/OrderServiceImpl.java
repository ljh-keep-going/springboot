package com.baidu.springboot07.service.impl;

import com.baidu.springboot07.pojo.Order;
import com.baidu.springboot07.dao.OrderMapper;
import com.baidu.springboot07.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
