package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @description: 课程类型
 * @author: YuanPeng
 * @create: 2020-02-14 17:04
 */
@Entity
@Getter
@Setter
@Table(name="course_type")
public class CourseType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 课程类型名称 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 课程类型描述 */
    @Column(name = "remark")
    private String remark;

    @JsonIgnore
    @OneToMany(mappedBy = "courseType")
    private Set<Course> courses;

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

    public void copy(CourseType source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}