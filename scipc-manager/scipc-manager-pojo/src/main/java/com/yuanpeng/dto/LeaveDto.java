package com.yuanpeng.dto;

import com.yuanpeng.entity.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description: 请假Dto
 * @author: YuanPeng
 * @create: 2020-02-14 17:45
 */
@Data
public class LeaveDto implements Serializable {

    private Long id;

    /** 请假时间 */
    private Timestamp datetime;

    /** 请假原因 */
    private String reason;

    private UserDto student;

    private Integer isRead;

    /** 是否批准(0: 未批准 1: 批准) */
    private Integer isApprove;

    /** 回复老师 */
    private UserDto teacher;

    /** 回复内容 */
    private String resContent;

    private Timestamp createTime;
}