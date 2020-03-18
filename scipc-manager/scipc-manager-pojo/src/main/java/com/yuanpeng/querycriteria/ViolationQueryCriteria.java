package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ViolationQueryCriteria {
    @Query
    private Long id;

    @Query(type = Query.Type.INNER_LIKE)
    private String reason;

    @Query(propName = "id", joinName = "student")
    private Long studentId;

    @Query(propName = "name", joinName = "student", type = Query.Type.INNER_LIKE)
    private String studentName;

    @Query(propName = "id", joinName = "teacher")
    private Long teacherId;

    @Query(propName = "name", joinName = "teacher", type = Query.Type.INNER_LIKE)
    private String teacherName;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
