package com.travelwink.kai.system.controller;

import com.travelwink.kai.framework.common.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ApiResult<String> hello() {
        return ApiResult.ok("Hello World!");
    }

}
