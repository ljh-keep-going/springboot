package com.baidu.supermarket.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer uid; //用户主键
    private String name; //用户名称
    private String password; //用户密码
    private Long telephone; //用户电话
    private Date createTime; //创建时间
    private Integer roleId; //角色主键

    private Role role; //连接角色表
}
