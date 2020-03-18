package com.yuanpeng.service;

import com.yuanpeng.dto.MessageDto;
import com.yuanpeng.dto.PermissionDto;
import com.yuanpeng.entity.Message;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.querycriteria.MessageQueryCriteria;
import com.yuanpeng.querycriteria.PermissionQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MessageService {
    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(MessageQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<PermissionDto>
     */
    List<MessageDto> queryAll(MessageQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return PermissionDto
     */
    MessageDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return PermissionDto
     */
    MessageDto create(Message resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Message resources);

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
    void download(List<MessageDto> all, HttpServletResponse response) throws IOException;

    void delete(Long id);

    void pushToStudent(Message resources);

    MessageDto pull();
}
