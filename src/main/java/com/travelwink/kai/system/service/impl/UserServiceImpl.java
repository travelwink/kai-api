package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.framework.exception.BusinessException;
import com.travelwink.kai.framework.pagination.PageModel;
import com.travelwink.kai.framework.pagination.PageResult;
import com.travelwink.kai.framework.properties.ShiroCryptoProperties;
import com.travelwink.kai.framework.utils.ShiroCryptoUtil;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.mapper.UserMapper;
import com.travelwink.kai.system.param.UserPageParam;
import com.travelwink.kai.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final ShiroCryptoProperties shiroCryptoProperties;

    @Autowired
    UserMapper userMapper;

    public UserServiceImpl(ShiroCryptoProperties shiroCryptoProperties) {
        this.shiroCryptoProperties = shiroCryptoProperties;
    }

    @Override
    public boolean createUser(User user) throws BusinessException {
        // 1. 验证用户名
        if (isUsernameExists(user.getUsername())) {
            log.error("Username: {} already exists", user.getUsername());
            throw new BusinessException("Username: [" + user.getUsername() + "] already exists");
        }

        // 2. 密码加盐密
        String userSalt = ShiroCryptoUtil.generateSalt();
        String systemSalt = shiroCryptoProperties.getSystemSalt();
        String combinedSalt = userSalt + systemSalt;
        user.setSalt(userSalt);
        user.setPassword(ShiroCryptoUtil.encryptPassword(user.getPassword(), combinedSalt));

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

    @Override
    public PageResult<User> getPageList(UserPageParam userPageParam) {
        PageModel<User> pageModel = new PageModel<>(userPageParam);
        PageModel<User> pageResult = userMapper.getPageList(pageModel, userPageParam);
        return new PageResult<>(pageResult);
    }

}
