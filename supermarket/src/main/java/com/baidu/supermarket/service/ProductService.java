package com.baidu.supermarket.service;

import com.baidu.supermarket.pojo.Product;
import com.baidu.supermarket.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductService {
    //分页查询所有商品信息
    public List<Product> getAllProduct(int currentPage,int pageSize);

    //查询商品总记录数
    public int count();

    //    获取满足查询条件，且指定了页号，页面大小的数据
    PageResult<Product> queryByPage(Integer page, Integer size, String prodName);

//    新增商品
    int addOne(Product prod);

    //    根据ID查询商品信息
    Product findById(Integer prodId);

    //    根据ID修改商品信息
    int editById(Product p);

    //    删除商品
    int delProduct(Integer prodId);

//    批量删除商品
    int batchDel(Integer[] ids);

//    读取月度销售数据
    List<Map<String, Object>> queryMonthSal(@Param("year") Integer year);

    //    查询总体销售品类占比
    List<Map<String, Object>> queryTotalRatio();
}
