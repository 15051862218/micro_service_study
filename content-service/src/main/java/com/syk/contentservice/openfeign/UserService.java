package com.syk.contentservice.openfeign;


import com.syk.contentservice.common.ResponseResult;
import com.syk.contentservice.openfeign.fallback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
//@FeignClient(value = "user-service",path = "/users",fallback = UserServiceFallback.class)
@FeignClient(value = "user-service",path = "/users",fallbackFactory = UserServiceFallbackFactory.class)
public interface UserService {

    /**
     * 调用用户服务
     *
     * @param id 用户id
     * @return 用户ResponseResult
     */
    @GetMapping("{id}")
    ResponseResult getUser(@PathVariable(value = "id") int id);
}
