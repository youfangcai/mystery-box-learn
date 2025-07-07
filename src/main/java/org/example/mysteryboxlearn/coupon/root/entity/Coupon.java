package org.example.mysteryboxlearn.coupon.root.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.dict.model.DictConstants;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券
 */
@Data
@TableName(value = "coupon")
public class Coupon extends BaseEntity {

    /**
     * 优惠券名称
     */
    @TableField(value = "name")
    String name;

    /**
     * 使用门槛
     */
    @TableField(value = "threshold_amount")
    BigDecimal thresholdAmount;

    /**
     * 发放数量
     */
    @TableField(value = "released_quantity")
    int releasedQuantity;

    /**
     * 优惠类型（打折，满减）
     */
    @TableField(value = "coupon_type")
    DictConstants.CouponType couponType;

    /**
     * 使用范围（通用，指定商品，类别）
     */
    @TableField(value = "scope_type")
    DictConstants.CouponScopeType scopeType;

    /**
     * 优惠金额
     */
    @TableField(value = "amount")
    BigDecimal amount;

    /**
     * 折扣
     */
    @TableField(value = "discount")
    BigDecimal discount;

    /**
     * 是否启用
     */
    @TableField(value = "status")
    boolean status;

    /**
     * 生效日期
     */
    @TableField(value = "effective_date")
    LocalDateTime effectiveDate;

    /**
     * 过期时间
     */
    @TableField(value = "expiration_date")
    LocalDateTime expirationDate;
}

