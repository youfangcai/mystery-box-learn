package org.example.mysteryboxlearn.product.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

@Data
@TableName(value = "product_category")
public class ProductCategory extends BaseEntity {

    @TableField(value = "name")
    private String name;

    @TableField(value = "parent_id")
    private ProductCategory parentId;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "description")
    private String description;

    @TableField(value = "sort_order")
    private int sortOrder;
}
