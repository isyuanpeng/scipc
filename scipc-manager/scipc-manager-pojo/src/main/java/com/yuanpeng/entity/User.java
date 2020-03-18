package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author YuanPeng
 * @date 2020-02-14
 */
@Entity
@Getter
@Setter
@Table(name="sys_user")
@Proxy(lazy = false)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /** 账户名(学号) */
    @Column(name = "username",nullable = false)
    @NotBlank
    private String username;

    /** 密码 */
    @Column(name = "password",nullable = false)
    private String password;

    /** 姓名 */
    @Column(name = "name")
    private String name;

    /** 手机号 */
    @Column(name = "phone")
    private String phone;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 专业班级 */
    @Column(name = "major")
    private String major;

    /** 学院 */
    @Column(name = "college")
    private String college;

    /** 年级 */
    @Column(name = "grade")
    private String grade;

    /** 入班年份 */
    @Column(name = "come_year")
    private String comeYear;

    /** 办公室地址 */
    @Column(name = "office")
    private String office;

    @Column(name = "qq")
    private String qq;

    @Column(name = "credit")
    private Float credit;

    @Column(name = "week_time")
    private Long weekTime;

    @Column(name = "learning_time")
    private Long learningTime;

    @Column(name = "violation_count")
    private Integer violationCount;

    /** 用户状态(1: 正常 2: 删除) */
    @Column(name = "status")
    private Integer status;

    /** 创建时间 */
    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    /** 最后一次修改时间 */
    @CreationTimestamp
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Timestamp updateTime;

    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @JsonIgnoreProperties("users")
    private Set<Role> roles;

    @OneToMany(mappedBy = "student")
    private Set<StudentCourse> studentCourses;

    public void copy(User source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    public @interface Update {}
}