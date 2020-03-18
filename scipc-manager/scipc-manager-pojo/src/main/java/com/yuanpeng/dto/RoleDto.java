package com.yuanpeng.dto;

import com.yuanpeng.entity.Permission;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 角色Dto
 * @author: YuanPeng
 * @create: 2020-02-15 19:08
 */
@Data
public class RoleDto implements Serializable {

    private Long id;

    /** 角色名称 */
    private String name;

    private String code;

    /** 描述 */
    private String remark;

    private Timestamp createTime;

    private Set<PermissionDto> permissions;

    private Set<UserSmallDto> users;
}