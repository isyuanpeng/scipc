package com.yuanpeng.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-07 12:36
 */
@Data
public class LocalFileDto implements Serializable {

    private Long id;

    private String name;

    private String path;

    private String suffix;

    private String type;

    private String size;

    private String operate;

    private Timestamp createTime;
}