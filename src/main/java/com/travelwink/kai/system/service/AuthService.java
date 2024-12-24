package com.travelwink.kai.system.service;

import com.travelwink.kai.system.param.SignInParam;
import com.travelwink.kai.system.param.SignUpParam;

public interface AuthService {

    boolean signUp(SignUpParam param);

    boolean signIn(SignInParam param);

    void signOut();
}