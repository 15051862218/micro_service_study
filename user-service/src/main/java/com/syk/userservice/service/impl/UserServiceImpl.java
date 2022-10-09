package com.syk.userservice.service.impl;


import com.syk.userservice.domain.dto.UserDto;
import com.syk.userservice.domain.entity.User;
import com.syk.userservice.repository.UserRepository;
import com.syk.userservice.service.UserService;
import com.syk.userservice.utils.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtOperator jwtOperator;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User login(UserDto userDto) {
        String password = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());

        // 2.设置用户信息
        return userRepository.findByMobileAndPassword(userDto.getMobile(), password);
    }

    @Override
    public User UploadImg(String multipartFile, Integer id) {
        User user = findById(id);
        user.setAvatar(multipartFile);
      return   userRepository.saveAndFlush(user);
    }
}
