package com.baidu.springboot07.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    private Integer pid;

    private Integer pquantity;

    private Integer uid;

    //配置购物车对应的用户
    private User user;

    //配置购物车对应的商品
    private Product product;
}
