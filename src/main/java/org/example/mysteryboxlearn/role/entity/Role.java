package org.example.mysteryboxlearn.role.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

/**
 * Entity for table "role"
 */
@Data
@TableName(value = "role")
public class Role extends BaseEntity {

    // 主键
    @TableField(value = "name")
    String name;
}

