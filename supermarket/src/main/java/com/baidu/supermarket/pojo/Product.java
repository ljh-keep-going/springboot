package com.baidu.supermarket.pojo;

import lombok.Data;

@Data
public class Product {
    private Integer prodId; //商品主键

    private String prodName; //商品名称
    private Double prodPrice; //商品价格
    private String pfile; //商品图片
    private String description; //商品描述

//    针对商品实体，1对多关联
//    private List<TOrder> prodOrders;
}
