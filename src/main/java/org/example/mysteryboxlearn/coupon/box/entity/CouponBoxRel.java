package org.example.mysteryboxlearn.coupon.box.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBox;
import org.example.mysteryboxlearn.coupon.root.entity.Coupon;
import org.example.mysteryboxlearn.common.base.BaseEntity;

/**
 * 优惠券可使用的盲盒
 */
@Data
@TableName(value = "coupon_box_rel")
public class CouponBoxRel extends BaseEntity {

    /**
     * 优惠券id
     */
    @TableField(value = "coupon_id")
    Coupon coupon;

    /**
     * 盲盒id
     */
    @TableField(value = "box_id")
    MysteryBox box;
}

