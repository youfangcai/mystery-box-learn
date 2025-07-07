package org.example.mysteryboxlearn.user.wechat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.mysteryboxlearn.common.base.BaseDateTime;


/**
 * Entity for table "user_we_chat"
 */
@Data
@TableName(value = "user_we_chat")
public class UserWeChat extends BaseDateTime {

    @TableId(value = "id")
    String id;

    @TableField(value = "open_id")
    String openId;

    @TableField(value = "user_id")
    String user;
}

