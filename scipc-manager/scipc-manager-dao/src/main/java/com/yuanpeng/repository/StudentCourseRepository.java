package com.yuanpeng.repository;


import com.yuanpeng.dto.StudentCourseDto;
import com.yuanpeng.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 学生课程Dao
 * @author: YuanPeng
 * @create: 2020-02-15 16:40
 */
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long>, JpaSpecificationExecutor<StudentCourse> {

    @Transactional
    @Modifying
    @Query(value = "update StudentCourse sc set sc.progress = sc.progress+1 where sc.course.id = ?1 and sc.student.id = ?2")
    void updateProgress(Long courseId, Long studentId);

    @Transactional
    @Modifying
    @Query(value = "update StudentCourse sc set sc.isAchieve = 1 where sc.course.id = ?1 and sc.student.id = ?2")
    void updateAchieve(Long courseId, Long studentId);

    @Query(value = "select sc from StudentCourse sc where sc.course.id = ?1 and sc.student.id = ?2")
    StudentCourse findByCourseAndStudent(Long courseId, Long studentId);

    Boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);
}
