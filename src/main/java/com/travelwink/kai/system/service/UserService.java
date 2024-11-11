package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.system.entity.User;

public interface UserService extends IService<User> {

    boolean createUser(User user);

    User getByUsername(String username);

    boolean isUsernameExists(String username);
}
