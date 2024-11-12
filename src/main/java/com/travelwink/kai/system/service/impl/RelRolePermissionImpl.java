package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.system.entity.RelRolePermission;
import com.travelwink.kai.system.mapper.RelRolePermissionMapper;
import com.travelwink.kai.system.service.RelRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RelRolePermissionImpl extends ServiceImpl<RelRolePermissionMapper, RelRolePermission> implements RelRolePermissionService {

    @Override
    public Set<String> getPermissionIdsByRoleIds(Set<String> roleIds) {
        LambdaQueryWrapper<RelRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RelRolePermission::getFkRoleId, roleIds);
        queryWrapper.select(RelRolePermission::getFkPermissionId);
        List<RelRolePermission> relRolePermissionList = list(queryWrapper);
        return relRolePermissionList.stream().map(RelRolePermission::getFkPermissionId).collect(Collectors.toSet());
    }
}
