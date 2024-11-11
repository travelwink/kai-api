package com.travelwink.kai.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "shiro.crypto")
public class ShiroCryptoProperties {

    /** 哈希算法 */
    private String hashAlgorithm;

    /** 迭代次数 */
    private int hashIterations;
}
