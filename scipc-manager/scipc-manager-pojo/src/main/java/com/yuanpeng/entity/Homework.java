package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 作业
 * @author: YuanPeng
 * @create: 2020-02-14 16:53
 */
@Entity
@Getter
@Setter
@Table(name="homework")
public class Homework implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 作业名 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 作业属于课程的第n章 */
    @Column(name = "course_chapter",nullable = false)
    @NotNull
    private String courseChapter;

    /** 课程id */
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Course course;

    /** 学生id */
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private User student;

    /** 作业描述 */
    @Column(name = "remark")
    private String remark;

    /** 文件位置 */
    @JoinColumn(name = "local_file_id", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private LocalFile file;

    @Column(name = "is_read")
    private Integer isRead;

    @Column(name = "score")
    private String score;

    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private User teacher;

    @Column(name = "response_content")
    private String responseContent;

    @JoinColumn(name = "response_file", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private LocalFile responseFile;

    @Column(name = "alloter")
    private String alloter;

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

    public void copy(Homework source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
