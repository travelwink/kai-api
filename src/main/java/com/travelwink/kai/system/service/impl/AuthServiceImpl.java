package com.travelwink.kai.system.service.impl;

import com.travelwink.kai.framework.enums.AccountStatus;
import com.travelwink.kai.framework.exception.BusinessException;
import com.travelwink.kai.framework.redis.entity.UserCache;
import com.travelwink.kai.framework.redis.repository.UserCacheRepository;
import com.travelwink.kai.framework.redis.service.UserCacheService;
import com.travelwink.kai.framework.utils.JwtUtil;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.param.SignInParam;
import com.travelwink.kai.system.param.SignUpParam;
import com.travelwink.kai.system.service.AuthService;
import com.travelwink.kai.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserCacheService userCacheService;

    @Autowired
    UserService userService;

    @Override
    public boolean signUp(SignUpParam param) {
        User user = userService.getByUsername(param.getUsername());
        if (ObjectUtils.isEmpty(user)) {
            user = new User();
            user.setUsername(param.getUsername());
            user.setPassword(passwordEncoder.encode(param.getPassword()));
            user.setEmail(param.getEmail());
        } else {
            throw new BusinessException("用户名已存在");
        }
        return userService.save(user);
    }

    @Override
    public String signIn(SignInParam param) {
        // todo 验证码校验逻辑
        User user = userService.getByUsername(param.getUsername());
        boolean isPasswordMatches = passwordEncoder.matches(param.getPassword(), user.getPassword());
        if (!isPasswordMatches) {
            int failureCount= userCacheService.incrementFailureCount(user.getUsername());
            if (failureCount >= 15) {
                user.setStatus(AccountStatus.LOCKED.getCode());
                userService.updateById(user);
                throw new CredentialsExpiredException("登录失败尝试次数用尽");
            } else {
                throw new CredentialsExpiredException("用户名密码错误，已尝试登录" + failureCount + "次");
            }
        }
        userCacheService.resetFailureCount(user.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return JwtUtil.generateToken(param.getUsername(), 1200);
//        Subject subject = SecurityUtils.getSubject();
//        AuthenticationToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
//        subject.login(token);
    }

    @Override
    public void signOut() {
//        SecurityUtils.getSubject().logout();
    }

}
