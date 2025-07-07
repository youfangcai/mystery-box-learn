package org.example.mysteryboxlearn.vip.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Repository
@AllArgsConstructor
public class VipConfigRepository {
    public static final String KEY = "vip-config";
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public VipConfig get() {
        String value = redisTemplate.opsForValue().get(KEY);
        if (StringUtils.hasText(value)) {
            return objectMapper.readValue(redisTemplate.opsForValue().get(KEY), VipConfig.class);
        }
        return new VipConfig().setDiscount(BigDecimal.TEN);
    }

    @SneakyThrows
    public void save(VipConfig vipConfig) {
        redisTemplate.opsForValue().set(KEY, objectMapper.writeValueAsString(vipConfig));
    }
}
