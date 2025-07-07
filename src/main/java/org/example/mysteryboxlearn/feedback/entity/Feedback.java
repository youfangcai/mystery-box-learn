package org.example.mysteryboxlearn.feedback.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;

import java.util.List;

/**
 * Entity for table "feedback"
 */
@Data
@TableName(value = "feedback")
public class Feedback extends BaseEntity {

    /**
     * 文本内容
     */
    @TableField(value = "content")
    String content;

    /**
     * 图片列表
     */
    @TableField(value = "pictures")
    List<String> pictures;
}

