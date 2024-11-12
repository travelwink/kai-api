package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.system.entity.RelRolePermission;

import java.util.Set;

public interface RelRolePermissionService extends IService<RelRolePermission> {
    Set<String> getPermissionIdsByRoleIds(Set<String> roleIds);
}
