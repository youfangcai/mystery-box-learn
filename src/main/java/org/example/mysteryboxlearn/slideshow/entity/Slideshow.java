package org.example.mysteryboxlearn.slideshow.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.dict.model.DictConstants;
import org.example.mysteryboxlearn.common.base.BaseEntity;

/**
 * Entity for table "slideshow"
 */
@Data
@TableName(value = "slideshow")
public class Slideshow extends BaseEntity {

    /**
     * 公告图片
     */
    @TableField(value = "picture")
    String picture;

    /**
     * 公告内容
     */
    @TableField(value = "content")
    String content;

    /**
     * 跳转目标id
     */
    @TableField(value = "navigator_id")
    String navigatorId;

    /**
     * 跳转类型
     */
    @TableField(value = "navigator_type")
    DictConstants.NavigatorType navigatorType;

    /**
     * 排序号
     */
    @TableField(value = "sort")
    int sort;

    /**
     * 是否显示
     */
    @TableField(value = "valid")
    boolean valid;
}

