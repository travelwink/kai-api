package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.system.entity.Role;
import com.travelwink.kai.system.mapper.RoleMapper;
import com.travelwink.kai.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Set<String> getCodeByIds(Set<String> roleIds) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Role::getId, roleIds);
        queryWrapper.select(Role::getCode);
        List<Role> roleList = super.list(queryWrapper);
        return roleList.stream().map(Role::getCode).collect(Collectors.toSet());
    }
}
