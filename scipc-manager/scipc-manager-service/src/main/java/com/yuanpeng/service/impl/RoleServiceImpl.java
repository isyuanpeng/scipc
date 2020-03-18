package com.yuanpeng.service.impl;

import com.yuanpeng.dto.RoleDto;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.entity.Role;
import com.yuanpeng.querycriteria.RoleQueryCriteria;
import com.yuanpeng.repository.RoleRepository;
import com.yuanpeng.service.RoleService;
import com.yuanpeng.service.mapper.RoleMapper;
import com.yuanpeng.common.utils.*;
import com.yuanpeng.service.mapper.RoleSmallMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: RoleServiceImpl
 * @author: YuanPeng
 * @create: 2020-02-15 19:11
 */
@Service
//@CacheConfig(cacheNames = "role")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    private final RoleSmallMapper roleSmallMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, RoleSmallMapper roleSmallMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.roleSmallMapper = roleSmallMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(RoleQueryCriteria criteria, Pageable pageable){
        Page<Role> page = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(roleMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<RoleDto> queryAll(RoleQueryCriteria criteria){
        return roleMapper.toDto(roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(),"Role","id",id);
        return roleMapper.toDto(role);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public RoleDto create(Role resources) {
        return roleMapper.toDto(roleRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Role resources) {
        Role role = roleRepository.findById(resources.getId()).orElseGet(Role::new);
        ValidationUtil.isNull( role.getId(),"Role","id",resources.getId());
        role.copy(resources);
        roleRepository.save(role);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            roleRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<RoleDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RoleDto role : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("角色名称", role.getName());
            map.put("描述", role.getRemark());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Collection<GrantedAuthority> mapToGrantedAuthorities(UserDto userDto) {
        Set<Role> roles = roleRepository.findByUsers_Id(userDto.getId());
        // 得到角色名称
        Set<String> rolenames = roles.stream().filter(role -> StringUtils.isNotBlank(role.getCode())).map(Role::getCode).collect(Collectors.toSet());
        // 角色名称和权限全部合在一起
        rolenames.addAll(
                roles.stream().flatMap(role -> role.getPermissions().stream())
                .filter(permission -> StringUtils.isNotBlank(permission.getExpression()))
                .map(Permission::getExpression).collect(Collectors.toSet())
        );
        // 转化为grantedAuthority
        return rolenames.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Map<String, Integer> count() {
        Map<String, Integer> map = new HashMap<>();
        List<Role> roles = roleRepository.findAll();
        for (Role role :
                roles) {
            map.put(role.getCode(), role.getUsers().size());
        }
        return map;
    }
}