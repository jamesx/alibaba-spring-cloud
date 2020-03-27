package com.august.core.handler;

import com.august.core.bean.Resp;
import com.august.core.exception.ParameterException;
import com.august.core.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UnauthorizedException.class})
    public Object handleUnauthorizedException(UnauthorizedException exception){
        log.error("系统出错!"+exception.getMessage());
        return Resp.fail(exception.getMessage());
    }

    @ExceptionHandler(value = {ParameterException.class})
    public Object handleParameterException(ParameterException exception){
        log.error("系统出错!"+exception.getMessage());
        return Resp.fail(exception.getMessage());
    }
}
