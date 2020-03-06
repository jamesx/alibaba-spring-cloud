package com.august.core.aop;

import com.august.core.bean.Resp;
import com.august.core.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Slf4j
//@Aspect
//@Component
public class GloableAspect {

    @Autowired
    GlobalConfig globalConfig;

    @Around("execution(* com.august..*Controller.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object proceed = null;
        try {
            log.debug("校验切面介入工作...."+globalConfig.getExceptionHandle());
            Object[] args = point.getArgs();
            for (Object obj:args){
                if(obj instanceof BindingResult){
                    BindingResult r = (BindingResult) obj;
                    if(r.getErrorCount()>0){
                        return Resp.fail(r.getFieldError().getDefaultMessage());
                    }
                }
            }
            proceed = point.proceed(point.getArgs());
        } catch (Throwable throwable) {
           throw  new RuntimeException(throwable);
        }finally {

        }
        return proceed;
    }

}
