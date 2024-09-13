package com.travelwink.kai.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.travelwink.kai.common.BaseEntity;
import com.travelwink.kai.validator.groups.Add;
import com.travelwink.kai.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6720732399829174039L;

    @Schema(description = "ID")
    @NotBlank(message = "ID不能为空", groups = {Update.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(example = "0", description = "用户类型")
    @NotNull(message = "用户类型不能为空", groups = {Add.class})
    private Integer type;

    @Schema(example = "admin", description = "用户名")
    @NotBlank(message = "用户名不能为空", groups = {Add.class})
    private String username;

    @Schema(example = "John", description = "姓名")
    private String realName;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空", groups = {Add.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,16}$",
            message = "密码应为8-16位，包含数字和字母，至少包含1个大写字母和1个特殊符号")
    private String password;

    @Schema(description = "盐值")
    @Null(message = "盐值不用传")
    private String salt;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(example = "1", description = "0:未知, 1:男, 2:女", defaultValue = "0")
    private Integer gender;

    @Schema(description = "头像")
    private String head;

    @Schema(example = "john@email.com", description = "")
    private String email;

    @Schema(description = "备注")
    private String remark;

    @Schema(example = "1", description = "0：禁用，1：启用，2：锁定",  defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "状态不能为空")
    private Integer state;

    @Schema(description = "企业编码")
    @NotBlank(message = "企业编码不能为空")
    private String enterpriseCode;

    @Schema(description = "角色ID集合")
    @NotNull(message = "角色ID不能为空")
    @TableField(exist = false)
    private Set<String> roleIds;

    @Schema(description = "岗位")
    private String job;

    @Schema(description ="逻辑删除;0：未删除，1：已删除", defaultValue = "0")
    @Null(message = "逻辑删除不用传")
    @TableLogic
    private Integer deleted;
}
