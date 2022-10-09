package com.syk.userservice.auth;


import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;
import com.syk.userservice.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.RequestContent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author 沈永康
 * @Date 2022/10/4 14:00
 * @Version 1.0
 */

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthAspect {
    private final JwtOperator jwtOperator;
    @Around(value = "@annotation(com.syk.userservice.auth.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint point) throws  Throwable {
        checkToken();;
        //使用反射去调用目标方法
        return point.proceed();
    }
    @Around(value = "@annotation(com.syk.userservice.auth.CheckAuthorization)")
    public Object checkAuthorization(ProceedingJoinPoint point) throws Throwable{
            try {
                this.checkToken();
                HttpServletRequest request = getHttpServletRequest();
                String role = (String) request.getAttribute("roles");
                MethodSignature signature = (MethodSignature) point.getSignature();
                Method method = signature.getMethod();
                CheckAuthorization annotation = method.getAnnotation(CheckAuthorization.class);
                String value = annotation.value();
                if (!Objects.equals(role,value)){
                    System.out.println(role);
                        throw new SecurityException("用户无权访问");

                }
            }catch (Throwable throwable){
                throw new SecurityException("用户无权访问",throwable);

            }
            return  point.proceed();
    }
    private void checkToken() {
        try {
            HttpServletRequest request = getHttpServletRequest();
            String token = request.getHeader("X-Token");
            Boolean isValid = jwtOperator.validateToken(token);
            if (!isValid) {
                throw new SecurityException("Token不合法");

            }
            Claims claims = jwtOperator.getClaimsFromToken(token);
            request.setAttribute("id",claims.get("id"));
            request.setAttribute("nickname",claims.get("nickname"));
            request.setAttribute("roles",claims.get("roles"));
        }catch (Throwable throwable) {
            throw new SecurityException("Token不合法");
        }
    }
    private HttpServletRequest getHttpServletRequest(){
        RequestAttributes  requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        assert attributes != null;
        return attributes.getRequest();
    }
}
