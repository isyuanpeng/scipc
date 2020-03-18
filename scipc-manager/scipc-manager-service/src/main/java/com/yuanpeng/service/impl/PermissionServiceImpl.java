package com.yuanpeng.service.impl;


import com.yuanpeng.dto.PermissionDto;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.querycriteria.PermissionQueryCriteria;
import com.yuanpeng.repository.PermissionRepository;
import com.yuanpeng.service.PermissionService;
import com.yuanpeng.service.mapper.PermissionMapper;
import com.yuanpeng.common.utils.FileUtil;
import com.yuanpeng.common.utils.PageUtil;
import com.yuanpeng.common.utils.QueryHelp;
import com.yuanpeng.common.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-18 11:53
 */
@Service
//@CacheConfig(cacheNames = "permission")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(PermissionQueryCriteria criteria, Pageable pageable){
        Page<Permission> page = permissionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(permissionMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<PermissionDto> queryAll(PermissionQueryCriteria criteria){
        return permissionMapper.toDto(permissionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public PermissionDto findById(Long id) {
        Permission permission = permissionRepository.findById(id).orElseGet(Permission::new);
        ValidationUtil.isNull(permission.getId(),"Permission","id",id);
        return permissionMapper.toDto(permission);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public PermissionDto create(Permission resources) {
        return permissionMapper.toDto(permissionRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Permission resources) {
        Permission permission = permissionRepository.findById(resources.getId()).orElseGet(Permission::new);
        ValidationUtil.isNull( permission.getId(),"Permission","id",resources.getId());
        permission.copy(resources);
        permissionRepository.save(permission);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            permissionRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PermissionDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PermissionDto permission : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("权限名称", permission.getName());
            map.put("权限描述", permission.getRemark());
            map.put("类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址", permission.getUrl());
            map.put("权限类型，页面-1，按钮-2", permission.getType());
            map.put("权限表达式", permission.getExpression());
            map.put("父级id", permission.getParentId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}