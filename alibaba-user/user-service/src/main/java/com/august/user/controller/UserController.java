package com.august.user.controller;

import com.august.commons.JwtUtil;
import com.august.core.annotation.AlibabaCache;
import com.august.core.bean.PageVo;
import com.august.core.bean.Resp;
import com.august.user.config.ResourceServerConfig;
import com.august.user.dto.UserDto;
import com.august.user.feign.OrderFeign;
import com.august.user.po.User;
import com.august.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OrderFeign orderFeign;

    public static final String USER_LIST = "user:list";


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private IUserService userService;

    @Value("${permitPath2}")
    private String permitPath2;

    @Value("${permitPath3}")
    private String permitPath3;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("permitPath: "+permitPath2);
        System.out.println("permitPath3: "+permitPath3);
        return "Hello~: ";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello2~: ";
    }

    @GetMapping("/userInfo")
    public Resp<Map> getUserInfo(){
        OAuth2AuthenticationDetails authentication = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Map<String, String> stringStringMap = JwtUtil.decodeToken(authentication.getTokenValue(), ResourceServerConfig.PUBLIC_KEY);
        return Resp.ok(stringStringMap);
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
    @AlibabaCache(prefix = USER_LIST, timeout = 7200, random = 100)
    @GetMapping("/list")
    public Resp<PageVo> list(@RequestParam Map<String, Object> params) {
        PageVo pageVo;
//        String json = stringRedisTemplate.opsForValue().get(USER_LIST);
//		if (StringUtils.isNotEmpty(json)) {
//            pageVo = JSON.parseObject(json, PageVo.class);
//            return Resp.ok(pageVo);
//		}
//
//        // 加分布式锁
//		RLock lock = redissonClient.getLock("lock" + USER_LIST);
//		lock.lock();
//
//        // 加锁之后再判断一次redis中有没有
//		String cacheDataString = stringRedisTemplate.opsForValue().get(USER_LIST);
//		if (StringUtils.isNotEmpty(cacheDataString)) {
//            pageVo = JSON.parseObject(cacheDataString, PageVo.class);
//		} else {
//            pageVo=userService.queryPage(queryCondition);
//            stringRedisTemplate.opsForValue().set(USER_LIST, JSON.toJSONString(pageVo), 7 + new Random().nextInt(5), TimeUnit.DAYS);
//		}
//        lock.unlock();

        pageVo=userService.queryPage(params);
        return Resp.ok(pageVo);
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
    public Resp<Object> save(@Valid @RequestBody UserDto userDto, BindingResult result){
//        if(result.getErrorCount()>0){
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            fieldErrors.forEach((fieldError)->{
//                String field = fieldError.getField();
//                log.debug("属性：{}，传来的值是：{}，校验出错。出错的提示消息：{}",
//                        field,fieldError.getRejectedValue(),fieldError.getDefaultMessage());
//            });
//            return Resp.fail(result.getAllErrors().toString());
//        }else {
//
//        }

        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userService.save(user);
/*
        Order order = new Order();
        order.setUserName("你好");*/
        orderFeign.remoteSave("zhangsan");
        //orderFeign.hello();

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
