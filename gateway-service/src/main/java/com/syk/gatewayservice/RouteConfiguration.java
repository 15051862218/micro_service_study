package com.syk.gatewayservice;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

/**
 * @Author 沈永康
 * @Date 2022/9/22 13:25
 * @Version 1.0
 */

//@Configuration
public class RouteConfiguration {
    @Bean
    public RouteLocator declare(RouteLocatorBuilder builder) {
        return  builder.routes().route("id-001",route -> route.path("/users/**")
                .and().method(HttpMethod.GET)
                .uri("http://localhost:8082"))
                .route("id-002",route -> route.path("/shares/**")
                        .uri("http://localhost:8083"))
                .build();

    }
}
