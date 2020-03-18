package com.yuanpeng.dto;

import com.yuanpeng.entity.Permission;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-17 23:34
 */
@Data
public class PermissionDto implements Serializable {
    private Long id;

    private String name;

    private String url;

    private String remark;

    private Integer type;

    private String expression;

    private Long parentId;

    private Timestamp createTime;
}
