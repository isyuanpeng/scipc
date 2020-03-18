package com.yuanpeng.service;

import com.yuanpeng.dto.RoleDto;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.Role;
import com.yuanpeng.querycriteria.RoleQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: RoleService
 * @author: YuanPeng
 * @create: 2020-02-15 19:10
 */
public interface RoleService {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(RoleQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<RoleDto>
     */
    List<RoleDto> queryAll(RoleQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return RoleDto
     */
    RoleDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return RoleDto
     */
    RoleDto create(Role resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Role resources);

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
    void download(List<RoleDto> all, HttpServletResponse response) throws IOException;

    /**
     * 将用户的角色信息和权限信息 -> GrantedAuthority
     * @param userDto
     * @return
     */
    Collection<GrantedAuthority> mapToGrantedAuthorities(UserDto userDto);

    void delete(Long id);

    Map<String, Integer> count();
}