package com.august.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "global")
@Data
public class GlobalConfig {
    private Boolean enabledCache;
    private Boolean enabledRbac;
    private Boolean enabledValidation;
}
