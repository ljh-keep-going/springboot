package com.baidu.supermarket.service.impl;

import com.baidu.supermarket.pojo.Product;
import com.baidu.supermarket.mapper.ProductMapper;
import com.baidu.supermarket.service.ProductService;
import com.baidu.supermarket.util.PageResult;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired(required = false)
    private ProductMapper mapper;

    //分页查询所有商品信息
    public List<Product> getAllProduct(int currentPage,int pageSize){
        int start=(currentPage-1)*pageSize;
        return mapper.getAllProduct(start,pageSize);
    }

    //查询商品总记录数
    public int count(){
        return mapper.count();
    }

    public PageResult<Product> queryByPage(Integer page, Integer size, String prodName) {
        PageResult<Product> result = new PageResult<Product>();
//      1、查询出满足查询条件的记录总数，赋值给 result.count 属性
        result.setCount(mapper.queryCount(prodName));
//      2、查询出满足查询条件的当前页的记录集合，赋值给 result.data 属性
        result.setData(mapper.queryData(prodName, (page - 1)*size, size));
        result.setCode(0);
        result.setMsg("查询成功");
        return result;
    }

    public int delProduct(Integer prodId) {
//        调用mybatis接口代理实例，执行删除的sql
//        log.debug("删除商品.pid={}", prodId);
        int i = mapper.delProduct(prodId);
//        log.debug("删除商品结果={}", i);
        return i;
    }

    public int batchDel(Integer[] ids) {
        return mapper.batchDel(ids);
    }

    public List<Map<String, Object>> queryMonthSal(Integer year) {
        return mapper.queryMonthSal(year);
    }


    public List<Map<String, Object>> queryTotalRatio() {
        return mapper.queryTotalRatio();
    }


    public int addOne(Product prod) {
        return mapper.addOne(prod);
    }


    public Product findById(Integer prodId) {
        return mapper.findById(prodId);
    }


    public int editById(Product p) {
//        log.info("修改商品成={}", p);
        return mapper.editById(p);
    }
}
