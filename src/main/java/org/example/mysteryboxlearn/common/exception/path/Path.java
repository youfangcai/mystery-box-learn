package org.example.mysteryboxlearn.common.exception.path;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

@Data
@TableName(value = "path")
public class Path extends BaseEntity {

    @TableField(value = "router")
    private String router;
}
