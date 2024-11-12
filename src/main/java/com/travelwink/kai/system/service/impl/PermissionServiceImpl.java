package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.framework.exception.BusinessException;
import com.travelwink.kai.system.entity.Permission;
import com.travelwink.kai.system.mapper.PermissionMapper;
import com.travelwink.kai.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public boolean createPermission(Permission permission) {
        // 验证编码是否已存在
        if (isExistByCode(permission.getCode())) {
            throw new BusinessException("权限编码已存在");
        }
        return save(permission);
    }

    @Override
    public Set<String> getPermissionCodesByIds(Set<String> permissionIds) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Permission::getId, permissionIds);
        queryWrapper.select(Permission::getCode);
        List<Permission> permissionList = list(queryWrapper);
        return permissionList.stream().map(Permission::getCode).collect(Collectors.toSet());
    }

    @Override
    public boolean isExistByCode(String code) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getCode, code);
        return exists(queryWrapper);
    }


}
