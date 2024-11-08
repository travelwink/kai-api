package com.travelwink.kai.system.controller;

import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.headers.Header;
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
    public ResponseEntity<Boolean> create(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user)) ;
    }

    @Operation(summary = "获取用户列表")
    @GetMapping("/getList")
    public ResponseEntity<List<User>> getList() {
        return ResponseEntity.ok(userService.list());
    }
}
