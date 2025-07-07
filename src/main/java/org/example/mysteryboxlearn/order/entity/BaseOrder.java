package org.example.mysteryboxlearn.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.address.entity.Address;
import org.example.mysteryboxlearn.coupon.user.entity.CouponUserRel;
import org.example.mysteryboxlearn.dict.model.DictConstants;
import org.example.mysteryboxlearn.common.base.BaseEntity;
import org.example.mysteryboxlearn.payment.entity.Payment;

/**
 * 基础订单
 */
@Data
@TableName(value = "base_order")
public class BaseOrder extends BaseEntity {

    /**
     * 支付订单
     */
    @TableField(value = "payment_id")
    Payment payment;

    /**
     * 地址详情
     */
    @TableField(value = "address_id")
    Address address;
    /**
     * 备注
     */
    @TableField(value = "remark")
    String remark;

    /**
     * 物流单号
     */
    @TableField(value = "tracking_number")
    String trackingNumber;

    /**
     * 用户优惠券id
     */
    @TableField(value = "coupon_user_id")
    CouponUserRel couponUser;

    /**
     * 订单类型
     */
    @TableField(value = "type")
    DictConstants.OrderType type;

}

