package com.syk.gatewayservice.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 沈永康
 * @Date 2022/10/3 10:07
 * @Version 1.0
 */

//@Configuration
public class TokenRelay {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users")
                        .filters(f -> f.tokenRelay())
                        .uri("http://localhost:8082"))
                .build();
    }
}
