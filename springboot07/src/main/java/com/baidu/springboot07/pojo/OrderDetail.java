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
@TableName("t_order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "opid", type = IdType.AUTO)
    private Integer opid;

    private Integer oid;

    private Integer pid;


}
