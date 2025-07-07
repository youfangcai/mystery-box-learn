package org.example.mysteryboxlearn.vip.root.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseEntity;
import org.example.mysteryboxlearn.user.root.entity.User;

import java.time.LocalDateTime;

/**
 * 会员信息
 */
@Data
@TableName(value = "vip")
public class Vip extends BaseEntity {

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    User user;

    /**
     * 到期时间
     */
    @TableField(value = "end_time")
    LocalDateTime endTime;
}

