package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:16
 */
@Data
public class CourseTypeQueryCriteria {
    @Query
    private Long id;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;
}
