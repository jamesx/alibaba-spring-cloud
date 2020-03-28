package com.august.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AlibabaCache {
    // 缓存中key的前缀
    String prefix() default "";

    // 过期时间 单位:分
    int timeout() default 5;

    // 随机时间 单位:分
    int random() default 5;
}
