package com.august.gateway.filter;

public class UrlFilter {

    private static final String noAuthorizationUrl="/api/user/login,/api/user/add";

    public static boolean hasAutorize(String uri){
        String[] split = noAuthorizationUrl.split(",");
        for (String s : split) {
            if(s.equals(uri)){
                //不需要拦截
                return true;
            }
        }
        //要拦截
        return false;
    }
}
