package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.mapper.UserMapper;
import com.travelwink.kai.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
