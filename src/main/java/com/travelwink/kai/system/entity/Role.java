package com.travelwink.kai.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.travelwink.kai.framework.common.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "角色实体类")
@TableName("t_role")
public class Role extends BaseEntity {

    @Schema(description = "角色编码", example = "admin")
    @NotBlank(message = "角色编码不能为空")
    private String code;

    @Schema(description = "角色名称", example = "系统管理员")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @Schema(description = "角色描述")
    private String description;

    @Schema(description = "角色状态")
    private String status;

}
