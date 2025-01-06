package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.framework.exception.BusinessException;
//import com.travelwink.kai.framework.utils.ShiroCryptoUtil;
import com.travelwink.kai.framework.pagination.PageModel;
import com.travelwink.kai.framework.pagination.PageResult;
import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.mapper.UserMapper;
import com.travelwink.kai.system.param.UserPageParam;
import com.travelwink.kai.system.service.RelUserRoleService;
import com.travelwink.kai.system.service.RoleService;
import com.travelwink.kai.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RelUserRoleService relUserRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean createUser(User user) {
        // 验证用户名是否已存在
        if (isUsernameExists(user.getUsername())) {
            log.error("Username: {} already exists", user.getUsername());
            throw new BusinessException("Username: [" + user.getUsername() + "] already exists");
        }

        // 密码加密处理
//        user.setSalt(ShiroCryptoUtil.generateSalt());
//        user.setPassword(ShiroCryptoUtil.encryptPassword(user.getPassword(), user.getSalt()));

        return super.save(user);
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = super.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(user)) {
            Set<String> roleIds = relUserRoleService.getRoleIdListByUserId(user.getId());
            user.setRoleList(roleService.listByIds(roleIds));
        } else {
            throw new BusinessException("用户名不存在");
        }
        return user;
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
