package com.yuanpeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuanpeng.entity.StudentCourse;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 用户Dto
 * @author: YuanPeng
 * @create: 2020-02-14 16:27
 */
@Data
public class UserDto implements Serializable {

    private Long id;

    /** 账户名(学号) */
    private String username;

    private String name;

    private Set<RoleSmallDto> roles;

    private Set<StudentCourseDto> studentCourses;

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

    private Integer status;

    private Timestamp createTime;

    private Float credit;

    private Long weekTime;

    private Long learningTime;

    private Integer violationCount;

    @JsonIgnore
    private String password;
}
