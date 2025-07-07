package org.example.mysteryboxlearn.common.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "tenant.map"
)
@Data
public class TenantMapProperty {
    private String key;
}
