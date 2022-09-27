package com.syk.userservice.repository;

import com.syk.userservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 根据手机号和密码查询用户
     *
     * @param mobile   手机号
     * @param password 密码
     * @return 用户信息
     */
    User findByMobileAndPassword(String mobile, String password);

}
