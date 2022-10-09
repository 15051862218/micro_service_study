package com.syk.gatewayservice.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

/**
 * @Author 沈永康
 * @Date 2022/9/27 19:37
 * @Version 1.0
 */

@Configuration
@Slf4j
public class DynamicRoutesLoader implements InitializingBean{
    @Resource
    private NacosConfigManager nacosConfigManager;
    @Resource
    private NacosConfigProperties configProperties;
    @Resource
    private DynamicRoutesListener dynamicRoutesListener;

    private static final String ROUTES_CONFIG = "routes-config.json";


    @Override
    public void afterPropertiesSet() throws Exception {
        String routes = nacosConfigManager.getConfigService().getConfig(ROUTES_CONFIG,configProperties.getGroup(),8085);
        dynamicRoutesListener.receiveConfigInfo(routes);
        nacosConfigManager.getConfigService().addListener(ROUTES_CONFIG,configProperties.getGroup(),dynamicRoutesListener);
    }
}
