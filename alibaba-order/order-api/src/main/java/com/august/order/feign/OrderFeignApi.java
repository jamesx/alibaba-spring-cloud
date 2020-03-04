package com.august.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("alibaba-order")
@RequestMapping("/order")
public interface OrderFeignApi {
    @GetMapping("/hello")
    public String hello();
}
