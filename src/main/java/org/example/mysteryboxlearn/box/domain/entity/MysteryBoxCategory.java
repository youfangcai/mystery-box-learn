package org.example.mysteryboxlearn.box.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

/**
 * 盲盒类别
 */
@Data
@TableName(value = "mystery_box_category")
public class MysteryBoxCategory extends BaseEntity {
    // 盲盒类别名称
    @TableField(value = "name")
    String name;

    // 盲盒类别图标
    @TableField(value = "icon")
    String icon;

    // 盲盒类别描述
    @TableField(value = "description")
    String description;

    // 排序号
    @TableField(value = "sort_order")
    Integer sortOrder;

}
