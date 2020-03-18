package com.yuanpeng.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-07 12:32
 */
@Setter
@Getter
@Entity
@Table(name="local_file")
@NoArgsConstructor
public class LocalFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**文件名 */
    @Column(name = "name")
    private String name;

    /**后缀 */
    @Column(name = "suffix")
    private String suffix;

    /** 路径 */
    @Column(name = "path")
    private String path;

    /** 类型 */
    @Column(name = "type")
    private String type;

    /** 大小 */
    @Column(name = "size")
    private String size;

    /** 操作人 */
    @Column(name = "operate")
    private String operate;

    @Column(name = "create_time")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Timestamp createTime;

    public LocalFile(String name, String suffix, String path, String type, String size, String operate) {
        this.name = name;
        this.suffix = suffix;
        this.path = path;
        this.type = type;
        this.size = size;
        this.operate = operate;
    }

    public void copy(LocalFile source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}