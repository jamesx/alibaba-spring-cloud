package com.august.user.service.impl;

import com.august.core.bean.PageVo;
import com.august.core.bean.Query;
import com.august.core.bean.QueryCondition;
import com.august.user.mapper.UserMapper;
import com.august.user.po.User;
import com.august.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author august
 * @since 2020-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }
}
