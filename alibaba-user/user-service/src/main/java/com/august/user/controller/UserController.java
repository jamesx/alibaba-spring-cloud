package com.august.user.controller;

import com.august.core.bean.PageVo;
import com.august.core.bean.QueryCondition;
import com.august.core.bean.Resp;
import com.august.user.feign.OrderFeign;
import com.august.user.po.User;
import com.august.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OrderFeign orderFeign;

    @Autowired
    private IUserService userService;

    @GetMapping("/hello")
    public String hello(String msg) {
        return "Hello~: "+msg;
    }

    //http://localhost:8081/user/orderFeign
    @GetMapping("/orderFeign")
    public String orderFeignApi(){
        return orderFeign.hello();
    }


    @ApiOperation("根据用户名获取用户信息")
    @GetMapping("/info")
    public Resp<User> findByUserName(String userName) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(userName!=null,User::getUserName,userName);
        User user = userService.getOne(userLambdaQueryWrapper);
        return Resp.ok(user);
    }

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = userService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{userId}")
    public Resp<User> info(@PathVariable("userId") Long userId){
        User user = userService.getById(userId);
        return Resp.ok(user);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    public Resp<Object> save(@RequestBody User user){
        userService.save(user);
        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    public Resp<Object> update(@RequestBody User user){
        userService.updateById(user);
        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    public Resp<Object> delete(@RequestBody Long[] userIds){
        userService.removeByIds(Arrays.asList(userIds));
        return Resp.ok(null);
    }

}
