package com.august.oauth.feign;

import com.august.user.feign.UserFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface UserFeign extends UserFeignApi {
}


