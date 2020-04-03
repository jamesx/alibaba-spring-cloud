package com.august.core.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 配置当前系统的线程池信息
 */
@Configuration
public class ThreadPoolConfig {

    @Autowired
    PoolProperties poolProperties;

    @PostConstruct
    private void genToken() {
        System.out.println("11111111"+poolProperties.getCoreSize());
        System.out.println("22222222"+poolProperties.getTest());
    }

    @Bean("mainThreadPoolExecutor")
    public ThreadPoolExecutor mainThreadPoolExecutor(PoolProperties poolProperties){
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(poolProperties.getQueueSize());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolProperties.getCoreSize(),
                poolProperties.getMaximumPoolSize(), 10,
                TimeUnit.MINUTES, deque);

        return threadPoolExecutor;
    }

    @Bean("otherThreadPoolExecutor")
    public ThreadPoolExecutor otherThreadPoolExecutor(PoolProperties poolProperties){
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(poolProperties.getQueueSize());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolProperties.getCoreSize(),
                poolProperties.getMaximumPoolSize(), 10,
                TimeUnit.MINUTES, deque);

        return threadPoolExecutor;
    }


}
