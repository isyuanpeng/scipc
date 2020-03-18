package com.yuanpeng.service.mapper;

import com.yuanpeng.common.base.BaseMapper;
import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.LocalFile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-07 12:37
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalFileMapper extends BaseMapper<LocalFileDto, LocalFile> {

}