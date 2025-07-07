package org.example.mysteryboxlearn.product.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;
import org.example.mysteryboxlearn.product.domain.entity.ProductCategory;
import org.example.mysteryboxlearn.product.model.KeyValue;

import java.math.BigDecimal;
import java.util.List;

@Data
@TableName(value = "product")
public class Product extends BaseEntity {

    /**
     * 名称
     */
    @TableField(value = "name")
    String name;

    /**
     * 价格
     */
    @TableField(value = "price")
    BigDecimal price;

    /**
     * 封面
     */
    @TableField(value = "cover")
    String cover;

    /**
     * 品牌
     */
    @TableField(value = "brand")
    String brand;

    /**
     * 类别id
     */

    @TableField(value = "category_id")
    String categoryId;

    /**
     * 描述
     */
    @TableField(value = "description")
    String description;

    /**
     * 标签
     */
    @TableField(value = "tags")
    List<String> tags;

    /**
     * 规格
     */
    @TableField(value = "specifications")
    List<KeyValue> specifications;

    /**
     * 属性
     */
    @TableField(value = "attributes")
    List<KeyValue> attributes;

    // 品质类型
    @TableField(value = "quality_type")
    String qualityType;


}
