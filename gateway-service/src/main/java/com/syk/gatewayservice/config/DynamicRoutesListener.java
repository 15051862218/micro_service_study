package com.syk.gatewayservice.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @Author 沈永康
 * @Date 2022/9/27 19:37
 * @Version 1.0
 */

@Component
@Slf4j
public class DynamicRoutesListener implements Listener {
    @Resource
    private  GatewayService gatewayService;

    @Override
    public Executor getExecutor() {
        log.info("getExecutor");
        return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        log.info("received routes changes {}",configInfo);
        List<RouteDefinition> definitions = JSON.parseArray(configInfo,RouteDefinition.class);
        gatewayService.updateRoute(definitions);
    }
}
