package com.baidu.springboot07.service;

import com.baidu.springboot07.pojo.Product;
import com.baidu.springboot07.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
public interface ProductService extends IService<Product> {

    //首页中需要用到的分页查询商品信息
    public List<Product> getProductByPage(Integer currentPage, Integer pageSize);

    //普通分页查询商品信息
    public PageResult<Product> getPage(String pname, Integer currentPage, Integer pageSize);
}
