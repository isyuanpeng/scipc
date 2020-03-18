package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class LearningQueryCriteria {
    @Query
    private Long id;

    @Query(propName = "id", joinName = "student")
    private Long studentId;

    @Query(propName = "name", joinName = "student", type = Query.Type.INNER_LIKE)
    private String studentName;

    @Query
    private Integer type;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
