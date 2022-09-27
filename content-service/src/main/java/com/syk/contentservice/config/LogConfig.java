package com.syk.contentservice.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 沈永康
 * @Date 2022/9/8 8:34
 * @Version 1.0
 */

@Configuration
public class LogConfig {
    @Bean
    Logger.Level feignLogger() {
        return  Logger.Level.FULL;
//        return  Logger.Level.NONE;
//        return  Logger.Level.BASIC;
//        return  Logger.Level.HEADERS;
    }
}
