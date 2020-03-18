package com.yuanpeng.repository;

import com.yuanpeng.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 课程Dao
 * @author: YuanPeng
 * @create: 2020-02-15 16:38
 */
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
}
