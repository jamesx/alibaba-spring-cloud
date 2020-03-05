package com.august.user.service;

import com.august.core.bean.PageVo;
import com.august.core.bean.QueryCondition;
import com.august.user.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author august
 * @since 2020-03-05
 */
public interface IUserService extends IService<User> {
    PageVo queryPage(QueryCondition params);
}
