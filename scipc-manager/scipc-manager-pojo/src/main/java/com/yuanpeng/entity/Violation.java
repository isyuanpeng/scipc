package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="violation_record")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 学生id */
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private User student;

    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private User teacher;

    @Column(name = "reason")
    private String reason;

    /** 创建时间 */
    @Column(name = "create_time")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Timestamp createTime;

    /** 最后一次修改时间 */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @UpdateTimestamp
    private Timestamp updateTime;

    public void copy(Violation source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    public @interface Update {}
}
