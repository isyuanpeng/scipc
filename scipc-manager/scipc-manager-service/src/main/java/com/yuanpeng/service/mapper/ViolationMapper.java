package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.ViolationDto;
import com.yuanpeng.entity.Violation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RoleSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ViolationMapper extends BaseMapper<ViolationDto, Violation> {
}
