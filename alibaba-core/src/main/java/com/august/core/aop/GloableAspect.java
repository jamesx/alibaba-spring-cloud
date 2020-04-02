package com.august.core.aop;

import com.august.core.bean.Constant;
import com.august.core.bean.Resp;
import com.august.core.bean.ResultEnum;
import com.august.core.config.GlobalConfig;
import com.august.core.exception.ParameterException;
import com.august.core.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Aspect
@Component
public class GloableAspect {

    @Value("#{'${permitPath}'.split(',')}")
    private String[] permitPath;

    @Autowired
    GlobalConfig globalConfig;

    @Around("execution(* com.august..*Controller.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object proceed = null;

        //System.out.println("permitPath"+permitPath);

        try {
            //rbac
            if(globalConfig.getEnabledRbac()){
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String requestPath = request.getServletPath();
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                PathMatcher matcher = new AntPathMatcher();
                AtomicReference<Boolean> hasAuthority= new AtomicReference<>(false);

                AtomicBoolean permit= new AtomicBoolean(false);
                if(permitPath!=null){
                    Arrays.asList(permitPath).forEach(item->{
                        if(matcher.match(item,requestPath)){
                            permit.set(true);
                        }
                    });
                }
                if(!permit.get()){
                    authorities.forEach(item->{
                        if(matcher.match(requestPath,item.toString())){
                            hasAuthority.set(true);
                        }
                    });
                    if(!hasAuthority.get()){
                        throw new UnauthorizedException(ResultEnum.UNAUTHORIZED);
                        //return Resp.fail("无权限访问");
                    }
                }
            }

            //验证
            if(globalConfig.getEnabledValidation()){
                Object[] args = point.getArgs();
                //System.out.println("args: "+args.length);
                for (Object obj:args){
                    if(obj instanceof BindingResult){
                        BindingResult r = (BindingResult) obj;
                        if(r.getErrorCount()>0){
                            throw new  ParameterException(Constant.PARAMETER_ERROR,r.getFieldError().getDefaultMessage());
                        }
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
