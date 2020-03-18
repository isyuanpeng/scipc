package com.yuanpeng.security.config;

import com.yuanpeng.common.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-18 10:19
 */
@Service(value = "el")
public class ElPermissionConfig {

    /**
     * 自定义了权限验证方式 如果拥有多权限验证则可以把统一拥有的权限放到这来验证
     * 比如说所有的方法admin权限都可以放行 那么就可以吧admin的验证放到这
     * @param permissions
     * @return
     */
    public Boolean check(String ...permissions){
        // 获取当前用户的所有权限
        List<String> elPermissions = SecurityUtils.getUserDetails().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return elPermissions.contains("admin") || elPermissions.contains("teacher") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }
}
