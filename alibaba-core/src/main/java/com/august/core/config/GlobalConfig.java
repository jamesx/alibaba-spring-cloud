package com.august.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "global")
@Data
public class GlobalConfig {
    private Boolean enabledCache;
    private Boolean enabledRbac;
    private Boolean exceptionHandle;
}
