package com.yuanpeng.service;

import com.yuanpeng.dto.PermissionDto;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.querycriteria.PermissionQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-18 11:52
 */
public interface PermissionService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(PermissionQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<PermissionDto>
     */
    List<PermissionDto> queryAll(PermissionQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return PermissionDto
     */
    PermissionDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return PermissionDto
     */
    PermissionDto create(Permission resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Permission resources);

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
    void download(List<PermissionDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);
}
