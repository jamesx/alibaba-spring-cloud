package com.august.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("alibaba-user")
@RequestMapping("/user")
public interface UserFeignApi {
    @GetMapping("/hello")
    public String hello();
}
