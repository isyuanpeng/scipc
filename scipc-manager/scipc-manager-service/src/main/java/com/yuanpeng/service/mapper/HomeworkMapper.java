package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.HomeworkDto;
import com.yuanpeng.dto.HomeworkScoreDto;
import com.yuanpeng.entity.Homework;
import com.yuanpeng.entity.HomeworkScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 09:34
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeworkMapper extends BaseMapper<HomeworkDto, Homework> {

    @Mapping(target = "homework", ignore = true)
    HomeworkScoreDto homeworkScoreToHomeworkScoreDto(HomeworkScore homeworkScore);
}
