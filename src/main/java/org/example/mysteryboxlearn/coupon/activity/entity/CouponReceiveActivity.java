package org.example.mysteryboxlearn.coupon.activity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.coupon.root.entity.Coupon;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * 优惠券领取活动
 */
@Data
@TableName(value = "coupon_receive_activity")
public class CouponReceiveActivity extends BaseEntity {

    /**
     * 优惠券id
     */
    @TableField(value = "coupon_id")
    Coupon coupon;

    /**
     * 活动生效时间
     */
    @TableField(value = "effective_date")
    LocalDateTime effectiveDate;

    /**
     * 活动失效时间
     */
    @TableField(value = "expiration_date")
    LocalDateTime expirationDate;
}

