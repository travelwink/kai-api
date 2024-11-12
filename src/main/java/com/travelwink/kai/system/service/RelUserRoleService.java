package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.system.entity.RelUserRole;

import java.util.Set;

public interface RelUserRoleService extends IService<RelUserRole> {
    Set<String> getRoleIdListByUserId(String userId);
}
