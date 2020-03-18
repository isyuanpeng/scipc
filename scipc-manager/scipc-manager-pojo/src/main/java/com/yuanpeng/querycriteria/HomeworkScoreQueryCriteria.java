package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:12
 */
public class HomeworkScoreQueryCriteria {

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
