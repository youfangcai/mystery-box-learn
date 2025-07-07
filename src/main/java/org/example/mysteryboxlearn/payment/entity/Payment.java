package org.example.mysteryboxlearn.payment.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.dict.model.DictConstants;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付详情
 */
@Data
@TableName(value = "payment")
public class Payment extends BaseEntity {

    /**
     * 支付类型（微信，支付宝等）
     */
    @TableField(value = "pay_type")
    DictConstants.PayType payType;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    LocalDateTime payTime;

    /**
     * 实付金额
     */
    @TableField(value = "pay_amount")
    BigDecimal payAmount;

    /**
     * vip优惠金额
     */
    @TableField(value = "vip_amount")
    BigDecimal vipAmount;

    /**
     * 优惠券优惠金额
     */

    @TableField(value = "coupon_amount")
    BigDecimal couponAmount;

    /**
     * 商品总价
     */
    @TableField(value = "product_amount")
    BigDecimal productAmount;

    /**
     * 邮费
     */
    @TableField(value = "delivery_fee")
    BigDecimal deliveryFee;

    /**
     * 外系统交易订单号
     */
    @TableField(value = "trade_no")
    String tradeNo;
}

