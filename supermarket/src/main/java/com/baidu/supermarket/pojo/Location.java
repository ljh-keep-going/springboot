package com.baidu.supermarket.pojo;

import lombok.Data;

@Data
public class Location {

    private Integer aid;
    private String realName;
    private String phone;
    private String identity;
    private String address;
    private Integer uid;
    private Integer oid; //关联订单表
}
