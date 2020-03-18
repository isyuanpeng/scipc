package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import com.yuanpeng.dto.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 请假
 * @author: YuanPeng
 * @create: 2020-02-14 17:02
 */
@Entity
@Getter
@Setter
@Table(name="leave_record")
public class Leave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 请假时间 */
    @Column(name = "datetime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp datetime;

    /** 请假原因 */
    @Column(name = "reason")
    private String reason;

    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private User student;

    @Column(name = "is_read")
    private Integer isRead;

    /** 是否批准(0: 未批准 1: 批准) */
    @Column(name = "is_approve")
    private Integer isApprove;

    /** 回复老师 */
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne
    private User teacher;

    /** 回复内容 */
    @Column(name = "res_content")
    private String resContent;

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

    public void copy(Leave source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}