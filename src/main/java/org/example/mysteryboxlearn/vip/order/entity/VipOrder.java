package org.example.mysteryboxlearn.vip.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;
import org.example.mysteryboxlearn.order.entity.BaseOrder;
import org.example.mysteryboxlearn.user.root.entity.User;
import org.example.mysteryboxlearn.vip.pack.entity.VipPackage;

/**
 * vip订单
 */
@Data
@TableName(value = "vip_order")
public class VipOrder extends BaseEntity {

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    User user;

    /**
     * vip套餐id
     */
    @TableField(value = "vip_package_id")
    VipPackage vipPackage;

    /**
     * 订单id
     */
    @TableField(value = "base_order_id")
    BaseOrder baseOrder;
}

