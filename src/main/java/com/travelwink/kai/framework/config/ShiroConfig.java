package com.travelwink.kai.framework.config;

import com.travelwink.kai.framework.auth.JwtCredentialsMatcher;
import com.travelwink.kai.framework.auth.JwtRealm;
import com.travelwink.kai.framework.auth.ShiroRedisCacheManager;
import com.travelwink.kai.framework.auth.UserRealm;
import com.travelwink.kai.framework.properties.ShiroCryptoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ShiroConfig {

    @Autowired
    private UserRealm userRealm;

    @Autowired
    private JwtRealm jwtRealm;

    @Autowired
    private ShiroCryptoProperties shiroCryptoProperties;

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // need to accept POSTs from the login form
        chainDefinition.addPathDefinition("/api/login", "anon");
        chainDefinition.addPathDefinition("/api/logout", "logout");

        chainDefinition.addPathDefinition("/api/**", "authc");

        return chainDefinition;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 实例化哈希凭证匹配器&加密设置
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(shiroCryptoProperties.getHashAlgorithm());
        hashedCredentialsMatcher.setHashIterations(shiroCryptoProperties.getHashIterations());
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        userRealm.setCacheManager(ShiroRedisCacheManager.builder().build());

        // 实例化JWT凭证匹配器
        JwtCredentialsMatcher jwtCredentialsMatcher = new JwtCredentialsMatcher();
        jwtRealm.setCredentialsMatcher(jwtCredentialsMatcher);

        // 多 Realm 认证策略 - AtLeastOneSuccessfulStrategy
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator);

        // 设置认证领域
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(jwtRealm);
        defaultWebSecurityManager.setRealms(realms);

        // 关闭 Shiro's built-in session
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
//        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        defaultWebSecurityManager.setSubjectDAO(defaultSubjectDAO);

        return defaultWebSecurityManager;
    }

}
