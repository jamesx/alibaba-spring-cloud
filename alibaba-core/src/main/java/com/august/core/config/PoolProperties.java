package com.august.core.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alibaba.pool")
public class PoolProperties {

    private Integer coreSize;
    private Integer maximumPoolSize;
    private Integer queueSize;
    private String test;


}
