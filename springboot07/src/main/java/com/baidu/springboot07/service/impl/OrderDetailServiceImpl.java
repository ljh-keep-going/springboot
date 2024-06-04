package com.baidu.springboot07.service.impl;

import com.baidu.springboot07.pojo.OrderDetail;
import com.baidu.springboot07.dao.OrderDetailMapper;
import com.baidu.springboot07.service.OrderDetailService;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
