package com.travelwink.kai.system.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "注册参数")
public class SignUpParam implements Serializable {
    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "用户名不可为空")
    private String username;

    @Schema(description = "密码", example = "123456")
    @NotBlank(message = "密码不可为空")
    private String password;

    @Schema(description = "E-mail地址", example = "admin@example.com")
    @NotBlank(message = "E-mail地址不可为空")
    private String email;
}
