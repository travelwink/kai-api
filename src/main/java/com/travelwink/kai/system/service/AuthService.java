package com.travelwink.kai.system.service;

import com.travelwink.kai.system.param.SignInParam;
import com.travelwink.kai.system.param.SignUpParam;

/**
 * 鉴权服务接口
 *
 * @author Chris Liao
 */
public interface AuthService {

    /**
     * 注册
     * <p>
     * <ol>
     *     <li>用户名是否重复</li>
     * </ol>
     *
     * @param param <code>SignUpParam</code> 注册账号信息：用户名 密码 Email
     * @return 注册是否成功
     */
    boolean signUp(SignUpParam param);

    boolean signIn(SignInParam param);

    void signOut();
}
