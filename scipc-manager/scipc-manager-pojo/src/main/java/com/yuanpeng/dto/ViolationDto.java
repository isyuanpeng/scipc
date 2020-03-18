package com.yuanpeng.dto;

import com.yuanpeng.entity.User;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class ViolationDto {

    private Long id;

    private User student;

    private User teacher;

    private String reason;

    private Timestamp createTime;

    private Timestamp updateTime;
}
