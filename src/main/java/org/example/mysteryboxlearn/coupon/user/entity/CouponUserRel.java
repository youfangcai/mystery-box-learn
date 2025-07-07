package org.example.mysteryboxlearn.coupon.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.coupon.root.entity.Coupon;
import org.example.mysteryboxlearn.common.base.BaseEntity;
import org.example.mysteryboxlearn.user.root.entity.User;

/**
 * 用户优惠券
 */
@Data
@TableName(value = "coupon_user_rel")
public class CouponUserRel extends BaseEntity {

    /**
     * 获得渠道（后台赠送，手动领取）
     */
    @TableField(value = "receive_type")
    String receiveType;

    /**
     * 优惠券id
     */
    @TableField(value = "coupon_id")
    Coupon coupon;

    /**
     * 使用状态（待使用，已使用，过期）
     */
    @TableField(value = "status")
    String status;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    User user;
}

