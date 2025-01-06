package com.travelwink.kai.system.controller;

import com.travelwink.kai.framework.common.ApiResult;
import com.travelwink.kai.system.param.SignInParam;
import com.travelwink.kai.system.param.SignUpParam;
import com.travelwink.kai.system.service.AuthService;
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

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@Validated @RequestBody SignUpParam param) {
        return ResponseEntity.ok(authService.signUp(param));
    }

    @PostMapping("/signIn")
    public ApiResult<String> signIn(@Validated @RequestBody SignInParam param) {
        String jwtToken = authService.signIn(param);
        return ApiResult.ok(jwtToken);
    }

    @GetMapping("/signOut")
    public ApiResult<Boolean> signOut() {
        authService.signOut();
        return ApiResult.ok();
    }
}
