package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.StudentCourseDto;
import com.yuanpeng.dto.StudentDto;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-06 11:46
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper extends BaseMapper<StudentDto, User> {

}