package com.travelwink.kai.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelwink.kai.framework.common.BaseEntity;
import com.travelwink.kai.framework.validator.groups.Create;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户实体类")
@TableName("t_user")
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6720732399829174039L;

    @Schema(example = "0", description = "用户类型")
    @NotNull(message = "用户类型不能为空", groups = {Create.class})
    private Integer type;

    @Schema(example = "admin", description = "用户名")
    @NotBlank(message = "用户名不能为空", groups = {Create.class})
    private String username;

    @Schema(example = "John", description = "用户昵称")
    private String nickName;

    @Schema(description = "密码")
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

    @Schema(example = "1", description = "0:未知, 1:男, 2:女", defaultValue = "0")
    private Integer gender;

    @Schema(description = "虚拟形象")
    private String avatar;

    @Schema(description = "电子邮箱地址", example = "john@email.com")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            message = "邮箱格式不正确")
    private String email;

    @Schema(description = "备注")
    private String remark;

    @Schema(example = "1", description = "状态: 0 禁用, 1 启用, 2 锁定",  defaultValue = "1")
    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    @Schema(description = "组织ID集合")
    @TableField(exist = false)
    private Set<String> orgIds;

    @Schema(description = "角色ID集合")
    @NotEmpty(message = "角色ID集合不能为空")
    @TableField(exist = false)
    private Set<String> roleIds;

    @Schema(description = "角色集合")
    @TableField(exist = false)
    private List<Role> roleList;

    @Schema(description = "头衔/称号 ID集合")
    @TableField(exist = false)
    private Set<String> titleIds;

}
