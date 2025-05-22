package com.travelwink.kai.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelwink.kai.framework.common.BaseEntity;
import com.travelwink.kai.framework.enums.AccountStatus;
import com.travelwink.kai.framework.properties.ShiroCryptoProperties;
import com.travelwink.kai.framework.validator.groups.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.Set;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户实体类")
@TableName("t_user")
public class User extends BaseEntity {

    private static ShiroCryptoProperties shiroCryptoProperties;

    @Serial
    private static final long serialVersionUID = 6720732399829174039L;

    @Schema(description = "用户类型", defaultValue = "0", example = "0")
    @Builder.Default
    private Integer type = 0;

    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "用户名不能为空", groups = {Create.class})
    private String username;

    @Schema(description = "用户昵称", example = "John")
    private String nickName;

    @Schema(description = "密码", hidden = true)
    @NotBlank(message = "密码不能为空", groups = {Create.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,16}$",
            message = "密码应为8-16位，包含数字和字母，至少包含1个大写字母和1个特殊符号")
    private String password;

    @JsonIgnore
    @Schema(description = "盐值")
    @Null(message = "盐值不用传")
    private String salt;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "0:未知, 1:男, 2:女", example = "1", defaultValue = "0")
    @Builder.Default
    private Integer gender = 0;

    @Schema(description = "虚拟形象")
    private String avatar;

    @Schema(description = "电子邮箱地址", example = "john@email.com")
    private String email;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "用户状态", type = "integer", example = "1", defaultValue = "1", allowableValues = {"0", "1", "2"})
    @Builder.Default
    @TableField(fill = FieldFill.INSERT)
    private AccountStatus status = AccountStatus.ENABLED;

    @Schema(description = "组织ID集合", type = "array")
    @NotEmpty(message = "组织ID集合不能为空")
    @TableField(exist = false)
    private Set<String> orgIds;

    @Schema(description = "角色ID集合", type = "array")
    @NotEmpty(message = "角色ID集合不能为空")
    @TableField(exist = false)
    private Set<String> roleIds;

    @Schema(description = "头衔/称号 ID集合", type = "array")
    @TableField(exist = false)
    private Set<String> titleIds;

}
