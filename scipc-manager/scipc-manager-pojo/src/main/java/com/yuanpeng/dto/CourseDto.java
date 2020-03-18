package com.yuanpeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yuanpeng.entity.CourseType;
import com.yuanpeng.entity.Homework;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 课程Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:38
 */
@Data
public class CourseDto implements Serializable {
    private Long id;

    private String code;

    private String name;

    private Integer chapterCount;

    private String url;

    private Float credit;

    private Integer isRequired;

    private String remark;

    @JsonIgnoreProperties(value = {"courses"})
    private CourseType courseType;

    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;
}
