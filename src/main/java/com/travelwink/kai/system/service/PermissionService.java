package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.system.entity.Permission;

import java.util.Set;

public interface PermissionService extends IService<Permission> {

    boolean createPermission(Permission permission);

    Set<String> getPermissionCodesByIds(Set<String> permissionIds);

    boolean isExistByCode(String code);
}
