package com.travelwink.kai.framework.security;

import com.travelwink.kai.system.entity.User;
import com.travelwink.kai.system.service.RelUserRoleService;
import com.travelwink.kai.system.service.RoleService;
import com.travelwink.kai.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    RelUserRoleService relUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        List<String> roleIdList = relUserRoleService.getRoleIdListByUserId(user.getId());
        user.setRoleList(roleService.listByIds(roleIdList));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities (User user) {
//      return roleService.getCodeByIds(user.getRoleIds()).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return user.getRoleList().stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .toList();
    }
}
