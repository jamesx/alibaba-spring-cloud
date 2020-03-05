package com.august.order.mapper;

import com.august.order.po.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author august
 * @since 2020-03-05
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
