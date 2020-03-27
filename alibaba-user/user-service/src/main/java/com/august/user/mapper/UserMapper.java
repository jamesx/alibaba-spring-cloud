package com.august.user.mapper;

import com.august.user.po.User;
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
public interface UserMapper extends BaseMapper<User> {

}
