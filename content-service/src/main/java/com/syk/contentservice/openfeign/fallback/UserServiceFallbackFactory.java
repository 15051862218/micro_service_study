package com.syk.contentservice.openfeign.fallback;



import com.syk.contentservice.common.ResponseResult;
import com.syk.contentservice.domain.entity.User;
import com.syk.contentservice.openfeign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author 沈永康
 * @Date 2022/9/9 11:25
 * @Version 1.0
 */

@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public  UserService create(Throwable cause) {
        return id -> {
            log.info("fallback factory method test" +cause);
             User user = User.builder().nickname("降级方案返回用户").avatar("defaut.jpg").build();
            return ResponseResult.success(user);
        };
    }
}
