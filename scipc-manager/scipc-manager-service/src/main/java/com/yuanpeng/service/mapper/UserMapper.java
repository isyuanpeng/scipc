package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-16 16:20
 */
@Mapper(componentModel = "spring", uses = {RoleSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {

}
