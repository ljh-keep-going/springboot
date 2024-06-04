package com.baidu.supermarket.mapper;


import com.baidu.supermarket.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    //分页查询所有商品信息
    @Select("select* from t_product limit #{start},#{pageSize}")
    @ResultMap("productBaseMap")
    public List<Product> getAllProduct(@Param("start") int start,@Param("pageSize") int pageSize);

    //查询商品总记录数
    @Select("select count(*) from t_product")
    public int count();

//    获取满足查询条件的商品的记录总数
    @Select("select count(pid) from t_product where pname like concat('%', #{prodName},'%')")
    Integer queryCount(@Param("prodName") String prodName);

//    获取满足条件的，且是当前页面的数据集合
    @Select("select pid,pname,price,pfile,description from t_product where pname like concat('%', #{prodName},'%') " +
            "limit #{offset}, #{limit}")
    @ResultMap("productBaseMap")
    List<Product> queryData(@Param("prodName") String prodName,
                            @Param("offset") Integer offset,
                            @Param("limit") Integer limit);

//    新增商品
    @Insert("insert into t_product(pname, price,pfile,description) values(#{prodName}, #{prodPrice},#{pfile},#{description})")
    int addOne(Product prod);

    //    根据ID查询商品信息
    @Select("select pid, pname, price,pfile,description from t_product where pid=#{prodId}")
    @ResultMap("productBaseMap")
    Product findById(@Param("prodId") Integer prodId);

    //    根据ID修改商品信息
    @Update("update t_product set pname=#{prodName}, price=#{prodPrice},pfile=#{pfile},description=#{description}" +
            " where pid=#{prodId}")
    int editById(Product p);

    //    删除商品
    @Delete("delete from t_product where pid=#{prodId}")
    int delProduct(@Param("prodId") Integer prodId);

//    批量删除商品
    int batchDel(@Param("ids") Integer[] ids);

//    读取月度销售数据
    List<Map<String, Object>> queryMonthSal(@Param("year") Integer year);
//    查询总体销售品类占比
    List<Map<String, Object>> queryTotalRatio();
}
