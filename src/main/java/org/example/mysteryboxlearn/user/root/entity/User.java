package org.example.mysteryboxlearn.user.root.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseDateTime;

/**
 * Entity for table "user"
 */
@Data
@TableName(value = "user")
public class User extends BaseDateTime {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "password")
    private String password;
    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "gender")
    private String gender;
    /**
     * 用户状态
     */
    @TableField(value =  "status")
    private String status;
}

