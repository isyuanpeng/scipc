package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.CourseTypeDto;
import com.yuanpeng.entity.CourseType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 23:29
 */
@Mapper(componentModel = "spring", uses = {CourseSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseTypeMapper extends BaseMapper<CourseTypeDto, CourseType> {

}
