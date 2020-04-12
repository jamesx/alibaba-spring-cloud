package com.august.core.aop;

import com.alibaba.fastjson.JSON;
import com.august.core.annotation.AlibabaCache;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class AlibabaCacheAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(com.august.core.annotation.AlibabaCache)")
    @Order(2)
    public Object IndexCacheAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解属性
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AlibabaCache indexCache = method.getAnnotation(AlibabaCache.class);
        // 获取缓存key的前缀
        String prefix = indexCache.prefix();

        //获取目标方法的参数列表
        Object[] args = joinPoint.getArgs();
        // 获取目标方法的返回值类型
        Class<?> returnType = method.getReturnType();

        String json;
        // 从缓存中查询
        String key = prefix + Arrays.asList(args).toString();
        json = redisTemplate.opsForValue().get(key);

        // 命中返回
        if (StringUtils.isNotEmpty(json)) {
            return JSON.parseObject(json, returnType);
        }

        // 没有命中,加分布式锁
        RLock lock = redissonClient.getLock("lock" + Arrays.asList(args).toString());
        lock.lock();

        // 再次查询缓存,命中返回
        json = redisTemplate.opsForValue().get(key);

        // 命中返回
        if (StringUtils.isNotEmpty(json)) {
            lock.unlock();
            return JSON.parseObject(json, returnType);
        }

        // 没有则执行目标方法
        Object result = joinPoint.proceed(args);

        // 放入缓存,释放分布式锁
        int timeout = indexCache.timeout();
        int random = indexCache.random();
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result), timeout + new Random().nextInt(random), TimeUnit.MINUTES);

        lock.unlock();

        return result;

    }
}
