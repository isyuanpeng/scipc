package com.yuanpeng.querycriteria;

import com.yuanpeng.common.annotation.Query;
import com.yuanpeng.dto.StudentDto;
import com.yuanpeng.dto.TeacherDto;
import com.yuanpeng.entity.Course;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 20:11
 */
@Data
public class StudentCourseQueryCriteria {
    @Query
    private Long id;

    @Query(propName = "id", joinName = "course")
    private Long courseId;

    @Query(propName = "name", joinName = "course", type = Query.Type.INNER_LIKE)
    private String courseName;

    @Query(propName = "id", joinName = "student")
    private Long studentId;

    @Query(propName = "name", joinName = "student", type = Query.Type.INNER_LIKE)
    private String studentName;

    @Query(propName = "id", joinName = "teacher")
    private Long teacherId;

    @Query(propName = "name", joinName = "teacher", type = Query.Type.INNER_LIKE)
    private String teacherName;

    @Query
    private Integer isAchieve;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
