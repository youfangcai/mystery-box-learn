package org.example.mysteryboxlearn.box.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.dict.model.DictConstants;
import org.example.mysteryboxlearn.common.base.BaseEntity;
import org.example.mysteryboxlearn.order.entity.BaseOrder;

/**
 * 盲盒订单
 */
@Data
@TableName(value = "mystery_box_order")
public class MysteryBoxOrder extends BaseEntity {
    @TableField(value = "base_order")
    BaseOrder baseOrder;

    /**
     * 订单状态
     */
    @TableField(value = "status")
    DictConstants.ProductOrderStatus status;
}

