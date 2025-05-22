package com.travelwink.kai.system.controller;

import com.travelwink.kai.framework.common.ApiResult;
import com.travelwink.kai.system.param.SignInParam;
import com.travelwink.kai.system.param.SignUpParam;
import com.travelwink.kai.system.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "注册")
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@Validated @RequestBody SignUpParam param) {
        return ResponseEntity.ok(authService.signUp(param));
    }

    @Operation(summary = "登录")
    @PostMapping("/signIn")
    public ApiResult<Boolean> login(@Validated @RequestBody SignInParam param) {
        boolean resultFlag = authService.signIn(param);
        return ApiResult.result(resultFlag);
    }

    @Operation(summary = "注销")
    @GetMapping("/signOut")
    public ApiResult<Boolean> signOut() {
        authService.signOut();
        return ApiResult.ok();
    }
}
