package com.travelwink.kai.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travelwink.kai.system.entity.RelUserRole;
import com.travelwink.kai.system.mapper.RelUserRoleMapper;
import com.travelwink.kai.system.service.RelUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RelUserRoleServiceImpl extends ServiceImpl<RelUserRoleMapper, RelUserRole> implements RelUserRoleService {
    @Override
    public Set<String> getRoleIdListByUserId(String userId) {
        LambdaQueryWrapper<RelUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RelUserRole::getUserId, userId);
        queryWrapper.select(RelUserRole::getRoleId);
        List<RelUserRole> relUserRoleList = super.list(queryWrapper);
        return relUserRoleList.stream().map(RelUserRole::getRoleId).collect(Collectors.toSet());
    }
}
