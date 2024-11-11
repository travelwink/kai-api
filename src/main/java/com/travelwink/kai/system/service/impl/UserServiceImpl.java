package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.framework.exception.BusinessException;
import com.travelwink.kai.framework.utils.ShiroCryptoUtil;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.mapper.UserMapper;
import com.travelwink.kai.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public boolean createUser(User user) {
        // 验证用户名是否已存在
        if (isUsernameExists(user.getUsername())) {
            log.error("Username: {} already exists", user.getUsername());
            throw new BusinessException("Username: [" + user.getUsername() + "] already exists");
        }

        // 密码加密处理
        user.setSalt(ShiroCryptoUtil.generateSalt());
        user.setPassword(ShiroCryptoUtil.encryptPassword(user.getPassword(), user.getSalt()));

        return super.save(user);
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean isUsernameExists(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return super.exists(queryWrapper);
    }

}
