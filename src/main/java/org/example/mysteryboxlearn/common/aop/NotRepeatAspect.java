package org.example.mysteryboxlearn.common.aop;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.mysteryboxlearn.common.exception.ResultCode;
import org.example.mysteryboxlearn.common.exception.BusinessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class NotRepeatAspect {
    private static final String USER_INVOKE = "userInvoke";
    private final StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(notRepeat)") //  匹配带有  NotRepeat 注解的方法
    private void notRepeatPointCut(NotRepeat notRepeat) {

    }

    @Around(value = "notRepeatPointCut(notRepeat)", argNames = "joinPoint,notRepeat")
    public Object forbidRepeat(ProceedingJoinPoint joinPoint, NotRepeat notRepeat) throws Throwable {
        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(USER_INVOKE + StpUtil.getLoginIdAsString(), "", 10, TimeUnit.SECONDS);
        if (ifAbsent == null || !ifAbsent) {
            throw new BusinessException(ResultCode.StatusHasInvalid, "请勿重复操作");
        }
        try {
            return joinPoint.proceed();
        } finally {
            redisTemplate.delete(USER_INVOKE + StpUtil.getLoginIdAsString());
        }
    }
}
