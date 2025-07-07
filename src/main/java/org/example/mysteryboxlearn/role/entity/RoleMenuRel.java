package org.example.mysteryboxlearn.role.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity for table "role_menu_rel"
 * *
 **/
@Data
@TableName(value = "role_menu_rel")
public class RoleMenuRel {
    @TableId(value = "role_id")
    private String roleId;

    @TableId(value = "menu_id")
    private String menuId;
}

