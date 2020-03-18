package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MessageQueryCriteria {
    @Query
    private Long id;

    @Query
    private Integer type;

    @Query(propName = "id", joinName = "sender")
    private Long senderId;

    @Query(propName = "name", joinName = "sender", type = Query.Type.INNER_LIKE)
    private String senderName;

    @Query(propName = "id", joinName = "reciver")
    private Long reciverId;

    @Query(propName = "name", joinName = "reciver", type = Query.Type.INNER_LIKE)
    private String reciverName;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
