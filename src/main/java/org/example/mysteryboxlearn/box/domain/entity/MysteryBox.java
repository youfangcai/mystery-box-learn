package org.example.mysteryboxlearn.box.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.math.BigDecimal;

@Data
@TableName(value = "mystery_box")
public class MysteryBox extends BaseEntity {
    @TableField(value = "name")
    String name;

    @TableField(value = "details")
    String details;

    @TableField(value = "tips")
    String tips;

    @TableField(value = "price")
    BigDecimal price;

    @TableField(value = "cover")
    String cover; // 封面

    @TableField(value = "category_id")
    String category; // 盲盒分类
}
