package com.travelwink.kai.system.controller;

import com.travelwink.kai.framework.common.ApiResult;
import com.travelwink.kai.framework.pagination.PageResult;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.param.UserPageParam;
import com.travelwink.kai.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户控制器API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "创建用户", description = "只能由已经登陆的、拥有系统管理中用户管理权限的用户进行的创建用户操作")
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
//    @RequiresPermissions("sys:user:create")
    public ApiResult<Boolean> create(@Validated @RequestBody User user) {
        return ApiResult.ok(userService.createUser(user));
    }

    @Operation(summary = "获取用户列表")
    @GetMapping("/getList")
//    @RequiresPermissions("sys:user:list")
    public ApiResult<List<User>> getList() {
        return ApiResult.ok(userService.list());
    }

    @Operation(summary = "获取用户分页列表")
    @PostMapping("/getPageList")
    public ApiResult<PageResult<User>> getPageList(@RequestBody UserPageParam userPageParam) {
        return ApiResult.ok(userService.getPageList(userPageParam));
    }
}
