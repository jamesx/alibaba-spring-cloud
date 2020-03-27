package com.august.oauth.interceptor;

import com.august.oauth.config.UserInfoAccessToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenRequestInterceptor implements RequestInterceptor {
    @Autowired
    UserInfoAccessToken userInfoAccessToken;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization","bearer "+userInfoAccessToken.getAccessToken());
        System.out.println("2222");
    }
}
