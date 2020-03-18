package com.yuanpeng.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yuanpeng.entity.Homework;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 学生Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:38
 */
@Data
public class StudentDto implements Serializable {

    private Long id;

    /** 学号 */
    private String username;

    /** 姓名 */
    private String name;

    /** 手机号 */
    private String phone;

    private String qq;

    /** 邮箱 */
    private String email;

    /** 专业班级 */
    private String major;

    /** 学院 */
    private String college;

    /** 年级 */
    private String grade;

    /** 入班年份 */
    private String comeYear;

    /** 办公室地址 */
    private String office;

    private Float credit;

    private Long weekTime;

    private Long learningTime;

    private Integer violationCount;
}
