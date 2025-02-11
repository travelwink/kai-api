package com.travelwink.kai.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS跨源配置类
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // 允许所有域名进行跨域调用，可以根据实际需求修改为具体的域名
        config.addAllowedOriginPattern("*");
        
        // 允许跨域发送cookie
        config.setAllowCredentials(true);
        
        // 放行全部原始头信息
        config.addAllowedHeader("*");
        
        // 允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再次预检
        config.setMaxAge(3600L);
        
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}