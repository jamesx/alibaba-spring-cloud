package com.august.user.service;

import com.august.core.bean.PageVo;
import com.august.user.po.AddressEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;


/**
 * 
 *
 * @author august
 * @email 379249906@qq.com
 * @date 2020-04-02 17:14:23
 */
public interface AddressService extends IService<AddressEntity> {

    PageVo queryPage(Map<String, Object> params);
}

