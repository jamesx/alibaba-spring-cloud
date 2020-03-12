package com.august.user.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RocketMqSource{
    String OUTPUT1 = "output1";
    @Output(RocketMqSource.OUTPUT1)
    MessageChannel output1();
}
