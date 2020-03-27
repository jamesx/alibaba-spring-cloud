package com.august.user.feign;

import com.august.core.bean.Resp;
import com.august.user.po.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
public interface UserFeignApi {
    @GetMapping("/hello")
    public String hello(@RequestParam("msg") String msg);

    @GetMapping("/info")
    public Resp<User> findByUserName(@RequestParam("userName") String userName);
}
