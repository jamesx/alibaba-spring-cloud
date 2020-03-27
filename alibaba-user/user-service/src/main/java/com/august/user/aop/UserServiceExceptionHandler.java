package com.august.user.aop;

import com.august.core.bean.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class})
    public Object handlerException(Exception exception){
        log.error("系统出错!",exception.getStackTrace());
        return Resp.fail("数学运算异常");
    }

//    @UserServiceExceptionHandler(value = {ArithmeticException.class})
//    public Object handlerException(Exception exception){
//        log.error("系统出错!",exception.getStackTrace());
//        return Resp.fail("数学运算异常");
//    }
}
