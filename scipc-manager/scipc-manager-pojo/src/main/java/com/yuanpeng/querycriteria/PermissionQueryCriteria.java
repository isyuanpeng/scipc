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
public class PermissionQueryCriteria {
    @Query
    private Long id;

    @Query(blurry = "name, url, expression")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

}
