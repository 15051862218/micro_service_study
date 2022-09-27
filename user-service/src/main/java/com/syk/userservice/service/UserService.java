package com.syk.userservice.service;

import com.syk.userservice.domain.dto.UserDto;
import com.syk.userservice.domain.entity.User;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
public interface UserService {
    /**
     * 根据id查找用户
     *
     * @param id id
     * @return User
     */
    User findById(Integer id);

    /**
     * 用户登录方法
     *
     * @param userDto 登录入参
     * @return 登录用户信息
     */
    User login(UserDto userDto);
}
