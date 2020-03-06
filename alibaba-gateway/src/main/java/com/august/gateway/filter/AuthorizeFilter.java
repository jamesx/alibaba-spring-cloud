package com.august.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器 :用于鉴权(获取令牌 解析 判断)
 *
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "Authorization";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //2.获取响应对象
        ServerHttpResponse response = exchange.getResponse();

        if(UrlFilter.hasAutorize(request.getURI().toString())){
            return chain.filter(exchange);
        }

        //从请求头中获取令牌数据
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        if(StringUtils.isEmpty(token)){
            //从cookie中中获取令牌数据
            HttpCookie cookieValue = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if(!StringUtils.isEmpty(cookieValue)){
                token=cookieValue.getValue();
            }
        }
        if(StringUtils.isEmpty(token)){
            //从请求参数中获取令牌数据
            token= request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            if(StringUtils.isEmpty(token)){
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        }

        if(!StringUtils.isEmpty(token)){
            request.mutate().header(AUTHORIZE_TOKEN,"Bearer "+ token);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
