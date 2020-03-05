package com.august.order.controller;

import com.august.order.feign.UserFeign;
import com.august.user.feign.UserFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    UserFeign userFeign;

    @GetMapping("/hello")
    public String hello() {
        return "Hello~";
    }

    //http://localhost:8082/order/userFeign
    @GetMapping("/userFeign")
    public String userFeignApi(){
        return userFeign.hello();
    }

}
