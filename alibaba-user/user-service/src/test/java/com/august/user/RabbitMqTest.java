package com.august.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitMqTest {
    @Autowired
    RabbitTemplate rabbitTemplate;  //操作k-v都是字符串的

    @Test
    public void contextLoads(){
        User user = new User("张三", "aaa.com");
        rabbitTemplate.convertAndSend("topicExchange","aa.hello",user);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class User implements Serializable {
        private String userName;
        private String email;
    }
}
