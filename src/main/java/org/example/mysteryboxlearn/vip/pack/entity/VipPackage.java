package org.example.mysteryboxlearn.vip.pack.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.math.BigDecimal;

/**
 * vip套餐
 */
@Data
@TableName(value = "vip_package")
public class VipPackage extends BaseEntity {

    /**
     * 套餐名称
     */
    @TableField(value = "name")
    String name;

    /**
     * 价格
     */
    @TableField(value = "price")
    BigDecimal price;

    @TableField(value = "days")
    int days;
}

