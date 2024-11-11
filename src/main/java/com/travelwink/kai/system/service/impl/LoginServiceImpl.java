package com.travelwink.kai.system.service.impl;

import com.travelwink.kai.system.param.LoginParam;
import com.travelwink.kai.system.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public void login(LoginParam param) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
        subject.login(token);
    }
}
