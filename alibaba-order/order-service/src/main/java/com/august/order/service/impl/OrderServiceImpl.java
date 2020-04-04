package com.august.order.service.impl;

import com.august.core.bean.PageVo;
import com.august.core.bean.Query;
import com.august.core.bean.QueryCondition;
import com.august.order.mapper.OrderMapper;
import com.august.order.po.Order;
import com.august.order.service.IOrderService;
import com.august.user.po.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author august
 * @since 2020-03-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<Order> page = this.page(
                new Query<Order>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    public static void main(String[] args) {
        PathMatcher matcher = new AntPathMatcher();
        String path = "/app/pub/login.do";
        String pattern = "/**/lo?in.do";
        boolean match = matcher.match(pattern, path);
        System.out.println(match);

    }
}
