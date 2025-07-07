package org.example.mysteryboxlearn.coupon.category.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.box.domain.entity.MysteryBoxCategory;
import org.example.mysteryboxlearn.coupon.root.entity.Coupon;
import org.example.mysteryboxlearn.common.base.BaseEntity;

/**
 * 优惠券可使用的商品类别
 */
@Data
@TableName(value = "coupon_category_rel")
public class CouponCategoryRel extends BaseEntity {

    /**
     * 优惠券id
     */
    @TableField(value = "coupon_id")
    Coupon coupon;


    /**
     * 分类id
     */
    @TableField(value = "category_id")
    MysteryBoxCategory category;
}

