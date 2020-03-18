package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:16
 */
@Data
public class LeaveQueryCriteria {

    @Query
    private Long id;

    @Query(propName = "id", joinName = "student")
    private Long studentId;

    @Query(propName = "name", joinName = "student", type = Query.Type.INNER_LIKE)
    private String studentName;

    @Query(propName = "id", joinName = "teacher")
    private Long teacherId;

    @Query(propName = "name", joinName = "teacher", type = Query.Type.INNER_LIKE)
    private String teacherName;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> datetime;
}
