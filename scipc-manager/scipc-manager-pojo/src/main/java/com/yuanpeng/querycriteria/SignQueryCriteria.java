package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import com.yuanpeng.entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 10:18
 */
@Data
public class SignQueryCriteria{
    @Query
    private Long id;

    @Query(propName = "id", joinName = "student")
    private Long studentId;

    @Query(propName = "name", joinName = "student", type = Query.Type.INNER_LIKE)
    private String studentName;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> updateTime;
}