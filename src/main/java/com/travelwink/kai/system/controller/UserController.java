package com.travelwink.kai.system.controller;

import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        return userService.save(user);
    }
}
