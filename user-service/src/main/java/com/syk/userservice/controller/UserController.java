package com.syk.userservice.controller;


import com.google.common.collect.Maps;
import com.syk.userservice.auth.CheckAuthorization;
import com.syk.userservice.auth.CheckLogin;
import com.syk.userservice.common.ResponseResult;
import com.syk.userservice.domain.dto.UserDto;
import com.syk.userservice.domain.entity.User;
import com.syk.userservice.domain.vo.UserVo;
import com.syk.userservice.service.UserService;
import com.syk.userservice.utils.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.syk.userservice.common.ResultCode.USER_SIGN_IN_FAIL;


/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final JwtOperator jwtOperator;
    private final StringRedisTemplate redisTemplate;

    @CheckLogin
    @GetMapping("{id}")
    public ResponseResult getUserById(@PathVariable Integer id) {
        User user =userService.findById(id);
        HashMap<String,User> hashMap = new HashMap<>();
        String redisKey = "用户"+ user.getId();
        if (hashMap.get(redisKey) == null){
            redisTemplate.opsForValue().set(redisKey,user.toString());
            hashMap.put(redisKey,user);
        }
        return ResponseResult.success(user);
    }
    @GetMapping("/ids")
    public ResponseResult getUserById2(@Param(value = "id") Integer id) {
        return ResponseResult.success(userService.findById(id));
    }
    @GetMapping("/test")
    public void Test(HttpServletRequest request) {
//        final val header = request.getHeader("X-Request-red");
        final var red = request.getParameter("red");
        System.out.println(red);
    }
    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        User userInfo = userService.login(userDto);
        HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("id", userInfo.getId());
        objectObjectHashMap.put("roles",userInfo.getRoles());
        String token = jwtOperator.generateToken(objectObjectHashMap);
        HashMap<String,String> infoMap = new HashMap<String, String>();
        if(redisTemplate.opsForValue().get("token") == null){
            redisTemplate.opsForValue().set("token",token);
            infoMap.put("token",token);
        }
        redisTemplate.expire("token",1000000, TimeUnit.SECONDS);
        UserVo userVo = new UserVo();
        userVo.setId(userInfo.getId());
        userVo.setToken(token);
        System.out.println(token);
        if (userInfo != null) {
            return ResponseResult.success(userVo);
        } else {
            return ResponseResult.failure(USER_SIGN_IN_FAIL);
        }
    }
   @PostMapping("/uploadImage")
    public ResponseResult uploadImage(@RequestParam String file,@RequestParam Integer id) {
        return  ResponseResult.success(userService.UploadImg(file,id));
   }
}
