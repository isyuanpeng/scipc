package com.yuanpeng.repository;

import com.yuanpeng.entity.HomeworkScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 作业成绩Dao
 * @author: YuanPeng
 * @create: 2020-02-15 16:42
 */
public interface HomeworkScoreRepository extends JpaRepository<HomeworkScore, Long>, JpaSpecificationExecutor<HomeworkScore> {
}
