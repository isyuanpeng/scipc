package com.yuanpeng.dto;

import com.yuanpeng.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 作业Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:45
 */
@Data
public class HomeworkDto implements Serializable {

    private Long id;

    /** 作业名 */
    private String name;

    /** 课程id */
    private CourseDto course;

    /** 作业属于课程的第n章 */
    private String courseChapter;

    /** 学生id */
    private StudentDto student;

    /** 作业描述 */
    private String remark;

    private Integer isRead;

    /** 文件位置 */
    private LocalFileDto file;

    private String score;

    private User teacher;

    private String responseContent;

    private LocalFileDto responseFile;

    private String alloter;

    private Timestamp createTime;
}