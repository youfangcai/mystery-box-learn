package org.example.mysteryboxlearn.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

@Data
@TableName(value = "dict")
public class Dict extends BaseEntity {

    @TableField(value = "key_id")
    int keyId;

    @TableField(value = "key_name")
    String keyName;

    @TableField(value = "key_en_name")
    String keyEnName;

    @TableField(value = "dict_id")
    int dictId;

    @TableField(value = "dict_name")
    String dictName;

    @TableField(value = "dict_en_name")
    String dictEnName;

    @TableField(value = "order_num")
    int orderNum;

    @TableField(value = "visible")
    boolean visible;
}
