package com.travelwink.kai.system.service.impl;

import com.travelwink.kai.system.param.LoginParam;
import com.travelwink.kai.system.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


public class LoginServiceImpl implements LoginService {
    @Override
    public boolean login(LoginParam param) {
        Subject subject = SecurityUtils.getSubject();
        return false;
    }
}
