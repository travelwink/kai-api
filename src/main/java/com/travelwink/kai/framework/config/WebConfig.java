package com.travelwink.kai.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebConfig {

    @Bean
    @Primary
    public RequestMappingHandlerMapping primaryRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

}
