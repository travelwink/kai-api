package com.travelwink.kai.framework.auth;

import com.travelwink.kai.framework.properties.ShiroCryptoProperties;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.lang.util.ByteSource;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Set;

@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    private final ShiroCryptoProperties shiroCryptoProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RelUserRoleService relUserRoleService;

    @Autowired
    private RelRolePermissionService relRolePermissionService;

    public UserRealm(ShiroCryptoProperties shiroCryptoProperties) {
        this.shiroCryptoProperties = shiroCryptoProperties;
    }

    /**
     * 获取授权信息(权限验证)
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return AuthorizationInfo 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取用户名
        String username = principals.getPrimaryPrincipal().toString();

        // 查询用户角色和权限
        String userId = userService.getByUsername(username).getId();
        Set<String> roleIds = relUserRoleService.getRoleIdListByUserId(userId);
        Set<String> roleCodes = roleService.getCodeByIds(roleIds);

        Set<String> permissionIds = relRolePermissionService.getPermissionIdsByRoleIds(roleIds);
        Set<String> permissionCodes = permissionService.getPermissionCodesByIds(permissionIds);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roleCodes);
        authorizationInfo.addStringPermissions(permissionCodes);
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息(登录验证)
     * <p>
     * <ol>
     *     <li>从 token 的 principal 中获取 name </li>
     *     <li>根据 username 查找数据库中的 User 实体</li>
     *     <li>将token中的principal，数据库中User实体的密码、盐值，用户名组合生成简单验证信息</li>
     * </ol>
     *
     * @param token 这个 AuthenticationToken 包含了 user 的主体 (principal) 和凭证 (credentials) .
     * @return AuthenticationInfo 验证信息
     * @throws UnknownAccountException 未知账号
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws UnknownAccountException {
        String name = token.getPrincipal().toString();
        User user = userService.getByUsername(name);
        if (ObjectUtils.isEmpty(user)) {
            log.error("There is no user with username of {}", token.getPrincipal());
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(
                token.getPrincipal(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt() + shiroCryptoProperties.getSystemSalt()),
                getName()
        );
    }
}
