package org.example.mysteryboxlearn.common.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "wx.pay"
)
@Data
public class WxPayPropertiesExtension {

    private String notifyUrl;
}