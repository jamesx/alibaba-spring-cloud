package com.august.order.feign;

import com.august.user.feign.UserFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("alibaba-user")
public interface UserFeign extends UserFeignApi {
}
