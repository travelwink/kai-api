package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.system.entity.RelUserRole;

import java.util.List;

public interface RelUserRoleService extends IService<RelUserRole> {
    List<String> getRoleIdListByUserId(String userId);
}
