package com.travelwink.kai.framework.config;

import com.travelwink.kai.framework.auth.UserRealm;
import com.travelwink.kai.framework.properties.ShiroCryptoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
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
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        // 加密设置
        hashedCredentialsMatcher.setHashAlgorithmName(shiroCryptoProperties.getHashAlgorithm());
        hashedCredentialsMatcher.setHashIterations(shiroCryptoProperties.getHashIterations());
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 多 Realm 认证策略
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator);

        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        defaultWebSecurityManager.setRealms(realms);

        // Close Shiro's built-in session
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(defaultSubjectDAO);

        return defaultWebSecurityManager;
    }
}
