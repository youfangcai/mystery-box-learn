package org.example.mysteryboxlearn.user.wechat.model;

import lombok.Data;

/**
 * 微信注册
 */
@Data
public class UserWeChatRegisterInput {

    private String inviteCode;
    private String loginCode;
    private String phone;
    private String code;
}
