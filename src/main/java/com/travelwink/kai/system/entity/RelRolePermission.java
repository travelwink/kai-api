package com.travelwink.kai.system.entity;

import com.travelwink.kai.framework.common.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色权限关系实体类")
public class RelRolePermission extends BaseEntity {

    @Schema(description = "角色ID")
    private String fkRoleId;

    @Schema(description = "角色ID")
    private String fkPermissionId;
}
