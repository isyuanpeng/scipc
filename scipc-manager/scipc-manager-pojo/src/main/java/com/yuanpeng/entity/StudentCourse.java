package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 学生课程
 * @author: YuanPeng
 * @create: 2020-02-14 16:51
 */
@Entity
@Getter
@Setter
@Table(name="student_course")
public class StudentCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 课程id */
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Course course;

    /** 学生id */
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JsonIgnoreProperties("studentCourses")
    private User student;

    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JsonIgnoreProperties("studentCourses")
    private User teacher;

    @Column(name = "score")
    private String score;

    /** 是否完成(0: 未完成 1:wjig ) */
    @Column(name = "is_achieve")
    private Integer isAchieve;

    /** 进度 也就是当前学到第几章 */
    @Column(name = "progress")
    private Integer progress;

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

    public void copy(StudentCourse source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}