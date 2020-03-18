package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 课程
 * @author: YuanPeng
 * @create: 2020-02-14 16:39
 */
@Entity
@Getter
@Setter
@Table(name="course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 课程代码 */
    @Column(name = "code")
    private String code;

    /** 课程名称 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 课程章节数 */
    @Column(name = "chapter_count")
    private Integer chapterCount;

    /** 网址 */
    @Column(name = "url")
    private String url;

    /** 学分 */
    @Column(name = "credit")
    private Float credit;

    /** 比选修(1: 必修 0: 选修) */
    @Column(name = "is_required")
    private Integer isRequired;

    /** 课程描述 */
    @Column(name = "remark")
    private String remark;

    /** 课程类型id */
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "course_type_id", referencedColumnName = "id")
    private CourseType courseType;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<StudentCourse> studentCourses;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Homework> homeworks;

    /** 课程状态(1: 正常 2: 删除) */
    @Column(name = "status")
    private Integer status;

    /** 创建时间 */
    @Column(name = "create_time")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Timestamp createTime;

    /** 最后一次修改时间 */
    @Column(name = "update_time")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Timestamp updateTime;

    public void copy(Course source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}