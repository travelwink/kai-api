package com.travelwink.kai.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    @Primary
    public RequestMappingHandlerMapping primaryRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    // 添加CORS配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有路径的跨域请求
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);// 设置预检请求的缓存时间（秒）
    }

}
