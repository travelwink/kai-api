package com.travelwink.kai.framework.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    // 签发方
    private String issuer;

    // 签发受众
    private String audience;

    // 过期时间
    private String expiration;

    // 密钥
    private String secretKey;
}
