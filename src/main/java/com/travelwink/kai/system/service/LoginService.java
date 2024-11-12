package com.travelwink.kai.system.service;

import com.travelwink.kai.system.param.LoginParam;

public interface LoginService {
    void login(LoginParam param);

    void logout();
}
