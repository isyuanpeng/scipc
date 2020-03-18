package com.yuanpeng.service;

import com.yuanpeng.dto.SignDto;
import com.yuanpeng.entity.Sign;
import com.yuanpeng.querycriteria.SignQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 10:18
 */
public interface SignService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(SignQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<SignDto>
     */
    List<SignDto> queryAll(SignQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return SignDto
     */
    SignDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return SignDto
     */
    SignDto create(Sign resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Sign resources);

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
    void download(List<SignDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);

    SignDto signIn();

    SignDto signOut(Sign resources);
}
