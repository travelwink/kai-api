package com.travelwink.kai.framework.auth;

import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.lang.util.ByteSource;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

@Slf4j
@Configuration
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 获取身份验证信息(登录验证)
     * @param token the authentication token containing the user's principal and credentials.
     * @return AuthenticationInfo 验证信息
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = token.getPrincipal().toString();
        User user = userService.getByUsername(name);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        log.error("There is no user with username of {}", token.getPrincipal());
        return new SimpleAuthenticationInfo(
                token.getPrincipal(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
    }
}
