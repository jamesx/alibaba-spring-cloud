package com.august.user.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Bean
	public RedissonClient redissonClient() {

		System.out.println("redisHost: "+redisHost);

		Config config = new Config();
		config.useSingleServer().setAddress("redis://" +redisHost+ ":6379");
		return Redisson.create(config);
	}
}
