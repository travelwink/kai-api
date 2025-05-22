package com.travelwink.kai.framework.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "shiro.crypto")
public class ShiroCryptoProperties {

    /**
     * 哈希算法
     */
    private String hashAlgorithm;

    /**
     * 迭代次数
     */
    private int hashIterations;

    /**
     * 系统盐值
     */
    private String systemSalt;
}
