package com.baidu.springboot07.service.impl;

import com.baidu.springboot07.pojo.Cart;
import com.baidu.springboot07.dao.CartMapper;
import com.baidu.springboot07.service.CartService;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
