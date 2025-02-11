package com.travelwink.kai.system.controller;

import com.travelwink.kai.framework.common.ApiResult;
import com.travelwink.kai.system.param.SignInParam;
import com.travelwink.kai.system.param.SignUpParam;
import com.travelwink.kai.system.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "AuthController", description = "鉴权控制器API")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "登记", description = "只能由已经登陆的、拥有系统管理中用户管理权限的用户进行的创建用户操作")
    @PostMapping("/signUp")
    public ApiResult<Boolean> signUp(@Validated @RequestBody SignUpParam param) {
        return ApiResult.ok(authService.signUp(param));
    }

    @Operation(summary = "登入", description = "只能由已经登陆的、拥有系统管理中用户管理权限的用户进行的创建用户操作")
    @PostMapping("/signIn")
    public ApiResult<String> signIn(@Validated @RequestBody SignInParam param) {
        String jwtToken = authService.signIn(param);
        return ApiResult.ok(jwtToken);
    }

    @Operation(summary = "登出", description = "只能由已经登陆的、拥有系统管理中用户管理权限的用户进行的创建用户操作")
    @GetMapping("/signOut")
    public ApiResult<Boolean> signOut() {
        authService.signOut();
        return ApiResult.ok();
    }
}
