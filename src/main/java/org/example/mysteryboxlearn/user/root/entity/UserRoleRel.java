package org.example.mysteryboxlearn.user.root.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity for table "user_role_rel" // 用户角色关系表，作为连接ManyToMany关系的中间表
 */
@Data
@TableName(value = "user_role_rel")
public class UserRoleRel{

    @TableId(value = "user_id")
    private String user;
    @TableId(value = "role_id")
    private String role;
}

