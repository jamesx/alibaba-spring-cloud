package com.august.user.controller;

import com.august.user.config.RocketMqSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mq")
public class RocketMqController {

    @Autowired
    private MessageChannel output; // 获取name为output的binding

    @GetMapping("/send")
    public void send() {
        String msgContent = "hello rocketmq!";
        output.send(MessageBuilder.withPayload(msgContent).build());
    }
}
