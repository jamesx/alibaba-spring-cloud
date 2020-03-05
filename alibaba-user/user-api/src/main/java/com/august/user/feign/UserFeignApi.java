package com.august.user.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserFeignApi {
    @GetMapping("/hello")
    public String hello();
}
