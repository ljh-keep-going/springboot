package com.baidu.springboot07.service.impl;

import com.baidu.springboot07.pojo.Product;
import com.baidu.springboot07.dao.ProductMapper;
import com.baidu.springboot07.service.ProductService;
import com.baidu.springboot07.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    //首页中需要用到的分页查询商品信息
    public List<Product> getProductByPage(Integer currentPage,Integer pageSize){
        //封装当前页和页面容量
        Page<Product> page=new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        //执行分页查询
        IPage<Product> iPage=productMapper.selectPage(page,null);

        //从Ipage对象中读取集合
        List<Product> list=iPage.getRecords();

        return list;
    }

    //普通分页查询商品信息
    @Override
    public PageResult<Product> getPage(String pname, Integer currentPage, Integer pageSize) {

        //创建PageResult<Product>对象
        PageResult<Product> pageResult = new PageResult<>();

        //设置code和msg的值
        pageResult.setCode(0);
        pageResult.setMsg("分页查询商品商品信息成功");

        //创建Page<product>对象，用于封装当前页和页面容量
        Page<Product> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        if (pname != null) {  //执行模糊分页
            //封装模糊查询条件
            queryWrapper.like("pname", pname);
        } else {  //普通分页
            queryWrapper = null;
        }

        //查询总记录数
        long count = productMapper.selectCount(queryWrapper);
        //强制类型转换
        int total = (int) count;

        //将总记录数保存到PageResult对象中
        pageResult.setCount(total);

        //分页查询
        IPage<Product> iPage = productMapper.selectPage(page, queryWrapper);

        //从iPage对象中获取List集合
        List<Product> list = iPage.getRecords();
        pageResult.setData(list);
        return pageResult;
    }
}
