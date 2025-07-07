package org.example.mysteryboxlearn.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BaseEntity extends BaseDateTime {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    // 使用ID作为主键
    String id;

    @TableField(value = "editor_id", fill = FieldFill.INSERT_UPDATE)
    String editor;

    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    String creator;
}
