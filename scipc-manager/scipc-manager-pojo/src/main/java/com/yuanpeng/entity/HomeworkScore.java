package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 作业成绩
 * @author: YuanPeng
 * @create: 2020-02-14 17:00
 */
@Entity
@Getter
@Setter
@Table(name="homework_score")
public class HomeworkScore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 作业id */
    @JoinColumn(name = "homework_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.ALL})
    private Homework homework;

    /** 老师id */
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User teacher;

    /** 分数 */
    @Column(name = "score",nullable = false)
    private String score;

    /** 评语 */
    @Column(name = "remark")
    private String remark;

    /** 创建时间 */
    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    /** 最后一次修改时间 */
    @Column(name = "update_time")
    @CreationTimestamp
    private Timestamp updateTime;

    public void copy(HomeworkScore source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}