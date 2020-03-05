package com.august.order.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
public interface OrderFeignApi {
    @GetMapping("/hello")
    public String hello();
}
