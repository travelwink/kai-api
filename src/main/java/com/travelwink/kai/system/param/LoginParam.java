package com.travelwink.kai.system.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "登录参数")
public class LoginParam implements Serializable {

    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "用户名不可为空")
    private String username;

    @Schema(description = "密码", example = "123456")
    @NotBlank(message = "密码不可为空")
    private String password;

    @Schema(description = "验证码")
    private String verifyCode;
}
