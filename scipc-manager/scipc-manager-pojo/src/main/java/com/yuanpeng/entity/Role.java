package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 角色
 * @author: YuanPeng
 * @create: 2020-02-15 19:07
 */
@Entity
@Getter
@Setter
@Table(name="sys_role")
@Proxy(lazy = false )
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /** 角色名称 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 代号 */
    @Column(name = "code")
    private String code;

    /** 描述 */
    @Column(name = "remark")
    private String remark;

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

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private Set<Permission> permissions;

    public void copy(Role source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    public @interface Update {}
}