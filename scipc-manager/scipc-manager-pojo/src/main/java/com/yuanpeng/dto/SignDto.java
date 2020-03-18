package com.yuanpeng.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 10:17
 */
@Data
public class SignDto implements Serializable {

    private Long id;

    /** 学生id */
    private UserDto student;

    /** 开始时间 */
    private Timestamp startTime;

    /** 结束时间 */
    private Timestamp endTime;

    private Long difference;
}