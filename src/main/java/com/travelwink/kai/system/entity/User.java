package com.travelwink.kai.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.travelwink.kai.common.BaseEntity;
import com.travelwink.kai.validator.groups.Add;
import com.travelwink.kai.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
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

    @SchemaProperty(name = "主键")
    @NotBlank(message = "ID不能为空", groups = {Update.class})
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @SchemaProperty(name = "用户类型")
    @NotNull(message = "用户类型不能为空", groups = {Add.class})
    private Integer type;

    @Schema(description = "用户名", examples = "admin")
    @NotBlank(message = "用户名不能为空", groups = {Add.class})
    private String username;

    @SchemaProperty(name = "姓名")
    private String realName;

    @SchemaProperty(name = "密码")
    @NotBlank(message = "密码不能为空", groups = {Add.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,16}$",
            message = "密码应为8-16位，包含数字和字母，至少包含1个大写字母和1个特殊符号")
    private String password;

    @SchemaProperty(name = "盐值")
    @Null(message = "盐值不用传")
    private String salt;

    @SchemaProperty(name = "手机号码")
    private String phone;

    @Schema(name = "性别", description = "0：女，1：男，默认1", examples = "1")
    private Integer gender;

    @SchemaProperty(name = "头像")
    private String head;

    @SchemaProperty(name = "Email")
    private String email;

    @SchemaProperty(name = "备注")
    private String remark;

    @Schema(name = "状态，0：禁用，1：启用，2：锁定", examples = "1", defaultValue = "1")
    @NotNull(message = "状态不能为空")
    private Integer state;

    @SchemaProperty(name = "企业编码")
    @NotBlank(message = "企业编码不能为空")
    private String enterpriseCode;

    @SchemaProperty(name = "角色ID集合")
    @NotNull(message = "角色ID不能为空")
    @TableField(exist = false)
    private Set<String> roleIds;

    @SchemaProperty(name = "岗位")
    private String job;

    @Schema(name = "逻辑删除", description = "0：未删除，1：已删除", examples = "0", defaultValue = "0")
    @Null(message = "逻辑删除不用传")
    @TableLogic
    private Integer deleted;
}
