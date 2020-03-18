package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:03
 */
@Data
public class UserQueryCriteria {

    @Query
    private Long id;

    @Query(blurry = "username, name, grade, college, major", type = Query.Type.INNER_LIKE)
    private String blurry;

    @Query
    private Integer status;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
