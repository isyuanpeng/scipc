package com.yuanpeng.repository;

import com.yuanpeng.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 作业Dao
 * @author: YuanPeng
 * @create: 2020-02-15 16:41
 */
public interface HomeworkRepository extends JpaRepository<Homework, Long>, JpaSpecificationExecutor<Homework> {
}
