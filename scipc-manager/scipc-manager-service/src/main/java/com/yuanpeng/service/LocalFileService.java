package com.yuanpeng.service;

import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.LocalFile;
import com.yuanpeng.querycriteria.LocalFileQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-07 12:39
 */
public interface LocalFileService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(LocalFileQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 条件
     * @return /
     */
    List<LocalFileDto> queryAll(LocalFileQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    LocalFileDto findById(Long id);

    /**
     * 上传
     * @param name 文件名称
     * @param file 文件
     * @return /
     */
    LocalFileDto create(String name, MultipartFile file);

    /**
     * 编辑
     * @param resources 文件信息
     */
    void update(LocalFile resources);

    /**
     * 多选删除
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     * @param localStorageDtos 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<LocalFileDto> localStorageDtos, HttpServletResponse response) throws IOException;

    LocalFileDto findByName(String name);

}