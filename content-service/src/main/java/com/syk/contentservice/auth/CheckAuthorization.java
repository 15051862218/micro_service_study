package com.syk.contentservice.auth;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author 沈永康
 * @Date 2022/10/4 13:59
 * @Version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
