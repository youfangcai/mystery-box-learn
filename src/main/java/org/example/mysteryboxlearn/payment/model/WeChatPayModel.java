package org.example.mysteryboxlearn.payment.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.mysteryboxlearn.order.entity.BaseOrder;

@Data
@Accessors(chain = true) // 链式调用
public class WeChatPayModel {
    private BaseOrder baseOrder;
    private String notifyUrl; // 微信回调地址
    private int expiredMinutes; // 过期时间，单位：分钟
}
