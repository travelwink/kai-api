package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.framework.pagination.PageResult;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.param.UserPageParam;

public interface UserService extends IService<User> {

    boolean createUser(User user);

    User getByUsername(String username);

    boolean isUsernameExists(String username);

    PageResult<User> getPageList(UserPageParam userPageParam);

}
