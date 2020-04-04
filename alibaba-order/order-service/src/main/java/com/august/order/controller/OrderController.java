package com.august.order.controller;

import com.august.core.bean.PageVo;
import com.august.core.bean.QueryCondition;
import com.august.core.bean.Resp;
import com.august.order.feign.UserFeign;
import com.august.order.po.Order;
import com.august.order.service.IOrderService;
import com.august.user.feign.UserFeignApi;
import com.august.user.po.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    UserFeign userFeign;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello~";
    }

    //http://localhost:8082/order/userFeign
    @GetMapping("/userFeign")
    public String userFeignApi(){
        return userFeign.hello("august");
    }

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    public Resp<PageVo> list(@RequestParam Map<String, Object> params) {
        PageVo page = orderService.queryPage(params);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{orderId}")
    public Resp<Order> info(@PathVariable("orderId") Long orderId){
        Order order = orderService.getById(orderId);
        return Resp.ok(order);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    public Resp<Object> save(@RequestBody Order order){
        orderService.save(order);
        return Resp.ok(null);
    }

    @ApiOperation("保存")
    @PostMapping("/remoteSave")
    public Resp<Object> remoteSave(String userName){
        Order order = new Order();
        order.setUserName(userName);
        orderService.save(order);
        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    public Resp<Object> update(@RequestBody Order order){
        orderService.updateById(order);
        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    public Resp<Object> delete(@RequestBody Long[] orderIds){
        orderService.removeByIds(Arrays.asList(orderIds));
        return Resp.ok(null);
    }


}
