package org.example.mysteryboxlearn.common.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotRepeat { // 在需要增强的方法（Join point）上添加这个注解

}
