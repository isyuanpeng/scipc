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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-17 23:16
 */

@Getter
@Setter
@Entity
@Table(name = "sys_permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /** 权限名 */
    @Column(name = "name")
    private String name;

    /** 描述 */
    @Column(name = "remark")
    private String remark;

    /** url */
    @Column(name = "url")
    private String url;

    /** type */
    @Column(name = "type")
    private Integer type;

    /** 权限表达式 */
    @Column(name = "expression")
    private String expression;

    /** 父级权限id */
    @Column(name = "parent_id")
    private Long parentId;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles;

    /** 创建时间 */
    @Column(name = "create_time")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Timestamp createTime;

    /** 最后一次修改时间 */
    @CreationTimestamp
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Timestamp updateTime;


    public void copy(Permission source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    public @interface Update {}

}
