package com.yuanpeng.service;

import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户服务
 * @author: YuanPeng
 * @create: 2020-02-14 17:16
 */
public interface UserService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(UserQueryCriteria criteria, Pageable pageable);

    Map<String, Object> queryAllByRoleCode(String code, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<UserDto>
     */
    List<UserDto> queryAll(UserQueryCriteria criteria);

    List<UserDto> queryAllByRole(String code);


    /**
     * 根据ID查询
     * @param id ID
     * @return UserDto
     */
    UserDto findById(Long id);

    /**
     * 根据Username查询
     * @param username
     * @return
     */
    UserDto findByUsername(String username);

    /**
     * 创建
     * @param resources /
     * @return UserDto
     */
    UserDto create(User resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(User resources);

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
    void download(List<UserDto> all, HttpServletResponse response) throws IOException;


    /**
     * 更新密码
     * @param username
     * @param newPassword
     */
    void updatePass(String username, String newPassword);

    Map<String, Long> getCount();

    void delete(Long id);

    void updateCenter(User resources);
}