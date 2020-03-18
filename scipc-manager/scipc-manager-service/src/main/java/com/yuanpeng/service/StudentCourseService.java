package com.yuanpeng.service;

import com.yuanpeng.dto.StudentCourseDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.StudentCourseQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 09:13
 */
public interface StudentCourseService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(StudentCourseQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<StudentCourseDto>
     */
    List<StudentCourseDto> queryAll(StudentCourseQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return StudentCourseDto
     */
    StudentCourseDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return StudentCourseDto
     */
    StudentCourseDto create(StudentCourse resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(StudentCourse resources);

    /**
     * 多选删除
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<StudentCourseDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);

    void correct(StudentCourse resources);

    void updateProgress(Course course, User user);

    Boolean isExist(Long courseId, Long studentId);
}