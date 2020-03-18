package com.yuanpeng.service;

import com.yuanpeng.dto.CourseDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.querycriteria.CourseQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 23:15
 */
public interface CourseService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(CourseQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<CourseDto>
     */
    List<CourseDto> queryAll(CourseQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return CourseDto
     */
    CourseDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return CourseDto
     */
    CourseDto create(Course resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Course resources);

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
    void download(List<CourseDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);
}