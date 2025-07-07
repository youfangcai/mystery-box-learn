package org.example.mysteryboxlearn.box.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

@Data
@TableName(value = "mystery_box_product_rel")
public class MysteryBoxProductRel extends BaseEntity {

    @TableField(value = "mystery_box_id")
    // 暴露关联实体的id字段
    String mysteryBoxId;

    @TableField(value = "product_id")
    String productId;
}
