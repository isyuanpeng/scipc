package com.yuanpeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 课程类型Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:39
 */
@Data
public class CourseTypeDto implements Serializable {

    private Long id;

    /** 课程类型名称 */
    private String name;

    /** 课程类型描述 */
    private String remark;

    private Set<CourseSmallDto> courses;

    private Timestamp createTime;

    private Timestamp updateTime;
}