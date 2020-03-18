package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:11
 */
@Data
public class CourseQueryCriteria {
    @Query
    private Long id;

    @Query(blurry = "name")
    private String blurry;

    @Query(propName = "name", joinName = "courseType", type = Query.Type.INNER_LIKE)
    private String courseTypeName;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
