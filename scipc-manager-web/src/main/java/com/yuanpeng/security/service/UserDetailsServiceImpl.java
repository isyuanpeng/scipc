package com.yuanpeng.security.service;

import com.yuanpeng.common.exception.BadRequestException;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.security.vo.JwtUser;
import com.yuanpeng.service.RoleService;
import com.yuanpeng.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-17 19:42
 */
@Service("userDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;

    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.findByUsername(username);
        if (userDto == null) {
            throw new BadRequestException("账号不存在");
        } else {
            if (userDto.getStatus() != 1) {
                throw new BadRequestException("账号未激活");
            }
            return createJwtUser(userDto);
        }
    }

    private UserDetails createJwtUser(UserDto user) {
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), roleService.mapToGrantedAuthorities(user));
    }
}
