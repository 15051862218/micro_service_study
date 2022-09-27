package com.syk.userservice.controller;


import com.syk.userservice.common.ResponseResult;
import com.syk.userservice.domain.dto.UserDto;
import com.syk.userservice.domain.entity.User;
import com.syk.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseResult getUserById(@PathVariable Integer id) {
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return ResponseResult.success(userService.findById(id));
    }

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody UserDto userDto) {
        User userInfo = userService.login(userDto);
        if (userInfo != null) {
            return ResponseResult.success(userInfo);
        } else {
            return ResponseResult.failure(USER_SIGN_IN_FAIL);
        }
    }

}
