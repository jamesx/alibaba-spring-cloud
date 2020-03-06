package com.august.user.aop;

import com.august.core.bean.Resp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Slf4j
@Aspect
@Component
public class GloableAspect {
    @Around("execution(* com.august..*Controller.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object proceed = null;
        try {
            log.debug("校验切面介入工作....");
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
