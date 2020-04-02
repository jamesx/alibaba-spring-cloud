package com.august.user.service.impl;

import com.august.core.bean.PageVo;
import com.august.core.bean.Query;
import com.august.user.mapper.AddressMapper;
import com.august.user.po.AddressEntity;
import com.august.user.service.AddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements AddressService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<AddressEntity> page = this.page(
                new Query<AddressEntity>().getPage(params),
                new QueryWrapper<AddressEntity>()
        );
        return new PageVo(page);
    }

}