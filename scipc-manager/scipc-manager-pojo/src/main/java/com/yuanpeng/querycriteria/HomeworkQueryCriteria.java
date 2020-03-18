package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:12
 */
@Data
public class HomeworkQueryCriteria {

    @Query
    private Long id;

    @Query(propName = "id", joinName = "course")
    private Long courseId;

    @Query(propName = "name", joinName = "course", type = Query.Type.INNER_LIKE)
    private String courseName;

    @Query(propName = "id", joinName = "student")
    private Long studentId;

    @Query(propName = "name", joinName = "student", type = Query.Type.INNER_LIKE)
    private String studentName;

    @Query
    private Integer isRead;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
