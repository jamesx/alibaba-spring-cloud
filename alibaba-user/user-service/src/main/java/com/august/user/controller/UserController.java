package com.august.user.controller;

import com.august.order.feign.OrderFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OrderFeignApi orderFeignApi;

    @GetMapping("/hello")
    public String hello() {
        return "Hello~";
    }

    //http://localhost:8081/user/orderFeign
    @GetMapping("/orderFeign")
    public String orderFeignApi(){
        return orderFeignApi.hello();
    }

}
