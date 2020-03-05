package com.august.user.feign;

import com.august.order.feign.OrderFeignApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("alibaba-order")
public interface OrderFeign extends OrderFeignApi {
}
