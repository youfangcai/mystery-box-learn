package org.example.mysteryboxlearn.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDateTime {
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    @TableField(value = "edited_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime editedTime;
}
