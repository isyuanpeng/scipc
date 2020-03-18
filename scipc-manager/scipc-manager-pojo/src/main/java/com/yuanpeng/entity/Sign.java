package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 10:14
 */
@Entity
@Data
@Table(name="sign")
public class Sign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 学生id */
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private User student;

    /** 开始时间 */
    @Column(name = "start_time",nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;

    /** 结束时间 */
    @Column(name = "end_time",nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;

    @Column(name = "difference")
    private Long difference;
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

    public void copy(Sign source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
