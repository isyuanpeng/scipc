package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.RoleSmallDto;
import com.yuanpeng.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-16 17:07
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {
}
