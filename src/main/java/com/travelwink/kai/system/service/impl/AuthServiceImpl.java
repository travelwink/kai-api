package com.travelwink.kai.system.service.impl;

import com.travelwink.kai.framework.exception.BusinessException;
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
    public boolean signIn(SignInParam param) {
        // todo 验证码校验逻辑
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userService.getByUsername(param.getUsername());
        return passwordEncoder.matches(param.getPassword(), user.getPassword());
//        Subject subject = SecurityUtils.getSubject();
//        AuthenticationToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
//        subject.login(token);
    }

    @Override
    public void signOut() {
//        SecurityUtils.getSubject().logout();
    }
}
