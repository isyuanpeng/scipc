package com.yuanpeng.service;

import com.yuanpeng.dto.LearningDto;
import com.yuanpeng.entity.Learning;
import com.yuanpeng.querycriteria.LearningQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LearningService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(LearningQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<HomeworkDto>
     */
    List<LearningDto> queryAll(LearningQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return HomeworkDto
     */
    LearningDto findById(Long id);

    /**
     * 创建
     * @param  /
     * @return HomeworkDto
     */
    LearningDto create(Learning resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Learning resources);

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
    void download(List<LearningDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);
}
