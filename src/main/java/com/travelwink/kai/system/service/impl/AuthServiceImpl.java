package com.travelwink.kai.system.service.impl;

import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.param.SignInParam;
import com.travelwink.kai.system.param.SignUpParam;
import com.travelwink.kai.system.service.AuthService;
import com.travelwink.kai.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserService userService;

    @Override
    public boolean signUp(SignUpParam param) {
        User user = User.builder()
                .username(param.getUsername())
                .password(param.getPassword())
                .email(param.getEmail())
                .build();
        userService.createUser(user);
        return true;
    }

    @Override
    public boolean signIn(SignInParam param) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
        subject.login(token);
        return true;
    }

    @Override
    public void signOut() {
        SecurityUtils.getSubject().logout();
    }
}
