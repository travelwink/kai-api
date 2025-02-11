package com.travelwink.kai.system.controller;

import com.travelwink.kai.framework.common.ApiResult;
import com.travelwink.kai.system.service.VerifyCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/verifyCode")
@Tag(name = "VerifyCodeController", description = "验证码控制器API")
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;


    @PostMapping("/send")
    public ApiResult<Boolean> send(@RequestBody String recipient) {
        return ApiResult.ok();
    }
}
