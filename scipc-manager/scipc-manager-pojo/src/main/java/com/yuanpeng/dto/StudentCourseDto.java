package com.yuanpeng.dto;

import com.yuanpeng.entity.Course;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 学生课程Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:45
 */
@Data
public class StudentCourseDto implements Serializable {

    private Long id;

    /** 课程id */
    private CourseDto course;

    /** 学生id */
    private StudentDto student;

    private TeacherDto teacher;

    private String score;

    /** 是否完成(0: 未完成 1:wjig ) */
    private Integer isAchieve;

    /** 进度 也就是当前学到第几章 */
    private Integer progress;

    private Timestamp createTime;

    private Timestamp updateTime;

}