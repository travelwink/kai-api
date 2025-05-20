package com.travelwink.kai.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthEntryPoint authEntryPoint;

    private final SecurityProperties securityProperties;

    public SecurityConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // 关闭防御CSRF攻击
//            .httpBasic(Customizer.withDefaults()) // 基本身份验证过滤器
//            .formLogin(Customizer.withDefaults()) // 表单身份验证过滤器
            .authorizeHttpRequests(authorize -> authorize
//                    .anyRequest().authenticated() // 授权过滤器
                    .requestMatchers(securityProperties.getIgnored().toArray(new String[0])).permitAll()
            )
            .exceptionHandling((exceptionHandling) -> {
                        exceptionHandling.authenticationEntryPoint(authEntryPoint);
                    }
            );

        return http.build();
    }


}
