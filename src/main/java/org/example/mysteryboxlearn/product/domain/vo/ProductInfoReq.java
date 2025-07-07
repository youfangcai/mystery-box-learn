package org.example.mysteryboxlearn.product.domain.vo;

import lombok.Data;
import org.example.mysteryboxlearn.product.model.KeyValue;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductInfoReq {
    /**
     * 名称
     */
    String name;

    /**
     * 价格
     */
    BigDecimal price;

    /**
     * 封面
     */
    String cover;

    /**
     * 品牌
     */
    String brand;

    /**
     * 类别id
     */
    String categoryId;

    /**
     * 描述
     */
    String description;

    /**
     * 标签
     */
    List<String> tags;

    /**
     * 规格
     */
    List<KeyValue> specifications;

    // 品质类型
    String qualityType;
    /**
     * 属性
     */
    List<KeyValue> attributes;
}
