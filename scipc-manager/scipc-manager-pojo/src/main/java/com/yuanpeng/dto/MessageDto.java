package com.yuanpeng.dto;

import com.yuanpeng.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class MessageDto implements Serializable {
    private Integer id;

    private User sender;

    private User reciver;

    private Integer isRead;

    private String content;

    private Integer type;    // 1： Announce 2: message

    private Timestamp createTime;
}
