package com.august.oauth.service;


import com.august.oauth.entity.AuthToken;

public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
