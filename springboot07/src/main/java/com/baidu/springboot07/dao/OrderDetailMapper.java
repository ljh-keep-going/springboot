package com.baidu.springboot07.dao;

import com.baidu.springboot07.pojo.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}
