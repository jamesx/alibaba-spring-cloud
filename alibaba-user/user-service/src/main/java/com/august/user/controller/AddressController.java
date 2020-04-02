package com.august.user.controller;

import com.august.core.bean.BaseController;
import com.august.user.mapper.AddressMapper;
import com.august.user.po.AddressEntity;
import com.august.user.service.impl.AddressServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 * @author august
 * @email 379249906@qq.com
 * @date 2020-04-02 17:14:23
 */
@RestController
@RequestMapping("address")
public class AddressController extends BaseController<AddressServiceImpl, AddressMapper,AddressEntity>{

}
