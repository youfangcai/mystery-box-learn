package org.example.mysteryboxlearn.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;
import org.example.mysteryboxlearn.common.exception.path.Path;

/**
 * Entity for table "menu"
 */
@Data
@TableName(value = "menu")
public class Menu extends BaseEntity {

    @TableField(value = "name")
    String name;

    @TableField(value = "path")
    Path path;

//    @TableField(value = "parent_id")
//    Menu parent;

    @TableField(value = "order_num")
    Integer orderNum;

    @TableField(value = "menu_type")
    String menuType;


    @TableField(value = "icon")
    String icon;

    @TableField(value = "visible")
    boolean visible;
}

