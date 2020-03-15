package com.august.order.feign;

import com.august.order.po.Order;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrderFeignApi {
    @GetMapping("/hello")
    public String hello();

    @PostMapping("/remoteSave")
    public String remoteSave(@RequestParam("userName") String userName);
}
