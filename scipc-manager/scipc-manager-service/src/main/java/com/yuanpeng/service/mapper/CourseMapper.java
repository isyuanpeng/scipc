package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.CourseDto;
import com.yuanpeng.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 23:16
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper extends BaseMapper<CourseDto, Course> {

}