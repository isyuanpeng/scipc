package com.yuanpeng.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-16 16:56
 */
@Data
public class RoleSmallDto implements Serializable {
    private Long id;

    private String name;

    private String code;
}