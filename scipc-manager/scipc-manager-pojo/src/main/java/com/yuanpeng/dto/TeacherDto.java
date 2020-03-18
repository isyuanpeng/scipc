package com.yuanpeng.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @description: 老师Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:38
 */
@Data
public class TeacherDto implements Serializable {

    private Long id;

    /** 教工号 */
    private String username;

    /** 姓名 */
    private String name;

    /** 电话 */
    private String phone;

    private String qq;

    /** 邮箱 */
    private String email;

    /** 学院 */
    private String college;

    /** 办公室地址 */
    private String office;
}
