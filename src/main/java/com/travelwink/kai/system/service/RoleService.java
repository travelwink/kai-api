package com.travelwink.kai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travelwink.kai.system.entity.Role;

import java.util.Set;

public interface RoleService extends IService<Role> {
    Set<String> getCodeByIds(Set<String> roleIds);
}
