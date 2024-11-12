package com.travelwink.kai.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.travelwink.kai.framework.common.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "权限实体类")
@TableName("t_permission")
public class Permission extends BaseEntity {

    @Schema(description = "权限编码", example = "sys:user:add")
    @NotBlank(message = "权限编码不能为空")
    private String code;

    @Schema(description = "权限名称", example = "系统管理:用户管理:添加用户")
    @NotBlank(message = "权限名称不能为空")
    private String name;

    @Schema(description = "权限URL", example = "/user/add")
    private String url;

    @Schema(description = "权限类型", example = "0: 界面模块 1:菜单 2:按钮")
    @NotNull(message = "权限类型不能为空")
    private Integer type;

    @Schema(description = "权限父ID", example = "0")
    private String parentId;

    @Schema(description = "权限层级", example = "1")
    private String lvl;

    @Schema(description = "权限图标", example = "")
    private String icon;

    @Schema(description = "权限排序", example = "0")
    private Integer sort;

    @Schema(description = "权限描述")
    private String description;

    @Schema(description = "权限状态")
    private Integer status;
}
