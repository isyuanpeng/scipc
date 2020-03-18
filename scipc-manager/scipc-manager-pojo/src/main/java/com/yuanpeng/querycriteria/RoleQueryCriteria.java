package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:10
 */
@Data
public class RoleQueryCriteria {
    @Query
    private Long id;

    @Query(blurry = "name, code")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
