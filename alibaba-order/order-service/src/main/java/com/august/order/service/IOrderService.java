package com.august.order.service;

import com.august.core.bean.PageVo;
import com.august.core.bean.QueryCondition;
import com.august.order.po.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author august
 * @since 2020-03-05
 */
public interface IOrderService extends IService<Order> {
    PageVo queryPage(QueryCondition params);
}
