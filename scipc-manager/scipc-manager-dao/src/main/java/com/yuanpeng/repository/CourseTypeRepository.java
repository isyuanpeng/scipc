package com.yuanpeng.repository;

import com.yuanpeng.entity.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 课程类型
 * @author: YuanPeng
 * @create: 2020-02-15 16:38
 */
public interface CourseTypeRepository extends JpaRepository<CourseType, Long>, JpaSpecificationExecutor<CourseType> {
}
