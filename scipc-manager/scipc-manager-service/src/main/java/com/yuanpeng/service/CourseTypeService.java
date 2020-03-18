package com.yuanpeng.service;

import com.yuanpeng.dto.CourseTypeDto;
import com.yuanpeng.entity.CourseType;
import com.yuanpeng.querycriteria.CourseTypeQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-23 23:29
 */
public interface CourseTypeService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(CourseTypeQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<CourseTypeDto>
     */
    List<CourseTypeDto> queryAll(CourseTypeQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return CourseTypeDto
     */
    CourseTypeDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return CourseTypeDto
     */
    CourseTypeDto create(CourseType resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(CourseType resources);

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
    void download(List<CourseTypeDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);
}