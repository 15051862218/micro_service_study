package com.syk.advertise.controller;


import com.syk.advertise.common.ResponseResult;
import com.syk.advertise.generator.service.AdvertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 沈永康
 * @Date 2022/9/24 18:03
 * @Version 1.0
 */

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/advertises")
public class AdvertiseServiceController {
    @Resource
    private AdvertiseService advertiseService;

    @GetMapping("/all")
    public ResponseResult getAllAdvertise() {
        return ResponseResult.success(advertiseService.getAdvertiseAll());
    }
}
