package com.travelwink.kai.system.controller;

import com.travelwink.kai.framework.common.ApiResult;
import com.travelwink.kai.system.entity.Permission;
import com.travelwink.kai.system.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
@Tag(name = "PermissionController", description = "权限控制器API")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Operation(summary = "创建权限", description = "只能由已经登陆的、拥有系统管理中权限管理权限的权限进行的创建权限操作")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "操作成功",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(description = "成功信息", implementation = ResponseEntity.class)
                            )),
                    @ApiResponse(
                            responseCode = "500",
                            description = "操作失败",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(description = "失败信息", implementation = ResponseEntity.class)
                            ))
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResult<Boolean>> create(@Validated @RequestBody Permission permission) {
        return ResponseEntity.ok(ApiResult.ok(permissionService.createPermission(permission)));
    }
}
