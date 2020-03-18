package com.yuanpeng.dto;

import com.yuanpeng.entity.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class LearningDto {

    private Long id;

    private User student;

    private Long duration;

    private Integer type;

    private Timestamp createTime;
}
