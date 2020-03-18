package com.yuanpeng.service;

import com.yuanpeng.dto.HomeworkDto;
import com.yuanpeng.entity.Homework;
import com.yuanpeng.entity.LocalFile;
import com.yuanpeng.entity.LocalFile;
import com.yuanpeng.querycriteria.HomeworkQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 09:24
 */
public interface HomeworkService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(HomeworkQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<HomeworkDto>
     */
    List<HomeworkDto> queryAll(HomeworkQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return HomeworkDto
     */
    HomeworkDto findById(Long id);

    /**
     * 创建
     * @param map /
     * @return HomeworkDto
     */
    HomeworkDto create(Map<String, Object> map, MultipartFile file);

    /**
     * 编辑
     * @param resources /
     */
    void update(Homework resources);

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
    void download(List<HomeworkDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);

    void correct(Long homeworkId, String score, String response, MultipartFile file);

    void allot(Long homeworkId, Long userId);
}