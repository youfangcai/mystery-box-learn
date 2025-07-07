package org.example.mysteryboxlearn.carriage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.carriage.entity.model.CarriageConfig;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.util.List;

/**
 * 运费模板
 */
@Data
@TableName(value = "carriage_template")
public class CarriageTemplate extends BaseEntity {

    /**
     * 模板名称
     */
    @TableField(value = "name")
    String name;

    /**
     * 运费模板描述
     */
    @TableField(value = "description")
    String description;

    /**
     * 运费配置
     */
    @TableField(value = "configs")
    // 序列化和反序列化注释
            List<CarriageConfig> configs;

    /**
     * 是否生效（全局只能存在一个生效）
     */
    @TableField(value = "valid")
    boolean valid;
}

