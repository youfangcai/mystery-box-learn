package org.example.mysteryboxlearn.user.wechat.model;

import lombok.Data;

/**
 * 微信注册
 */
@Data
public class UserWeChatRegisterInputV2 {

    String phoneCode;
    String loginCode;
    String inviteCode;
}
