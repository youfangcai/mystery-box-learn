package org.example.mysteryboxlearn.box.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

// 用于联系盲盒订单和盲盒， 用于统计用户购买的盲盒数量
@Data
@TableName(value = "myster_box_order_item")
public class MysteryBoxOrderItem extends BaseEntity {

    @TableId(value = "mystery_box_id")
    String mysteryBoxId;

    @TableField(value = "myster_number")
    int mysteryNumber;

    @TableField(value = "myster_box_order")
    MysteryBoxOrder mysteryBoxOrder;
}
