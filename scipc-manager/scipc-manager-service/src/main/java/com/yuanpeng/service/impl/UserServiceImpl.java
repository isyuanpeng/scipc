package com.yuanpeng.service.impl;

import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.Role;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.UserQueryCriteria;
import com.yuanpeng.repository.RoleRepository;
import com.yuanpeng.repository.UserRepository;
import com.yuanpeng.service.UserService;
import com.yuanpeng.service.mapper.StudentMapper;
import com.yuanpeng.service.mapper.TeacherMapper;
import com.yuanpeng.service.mapper.UserMapper;
import com.yuanpeng.common.utils.FileUtil;
import com.yuanpeng.common.utils.PageUtil;
import com.yuanpeng.common.utils.QueryHelp;
import com.yuanpeng.common.utils.ValidationUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @description: 用户服务Impl
 * @author: YuanPeng
 * @create: 2020-02-15 18:56
 */
@Service
//@CacheConfig(cacheNames = "user")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final StudentMapper studentMapper;

    private final TeacherMapper teacherMapper;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, StudentMapper studentMapper, TeacherMapper teacherMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(UserQueryCriteria criteria, Pageable pageable){
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }

    @Override
    public Map<String, Object> queryAllByRoleCode(String code, Pageable pageable) {
        Map<String, Object> result = new HashMap<>();
        Optional<Role> role = roleRepository.findByCode(code);
        Page<User> page = userRepository.findByRoles_Id(role.get().getId(), pageable);
        if (code.equals("student")) {
            result.put("student", PageUtil.toPage(page.map(studentMapper::toDto)));
        }
        if (code.equals("teacher")) {
            result.put("teacher", PageUtil.toPage(page.map(teacherMapper::toDto)));
        }
        if (code.equals("admin")) {
            result.put("admin", PageUtil.toPage(page.map(userMapper::toDto)));
        }
        if (code.equals("assistant")) {
            result.put("assistant", PageUtil.toPage(page.map(userMapper::toDto)));
        }
        return result;
    }

    @Override
    //@Cacheable
    public List<UserDto> queryAll(UserQueryCriteria criteria){
        return userMapper.toDto(userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public List<UserDto> queryAllByRole(String code) {
        return null;
    }

    @Override
    //@Cacheable(key = "#p0")
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(),"User","id",id);
        return userMapper.toDto(user);
    }

    @Override
    //@Cacheable(key = "#p0")
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        ValidationUtil.isNull(user.getId(),"User","username", username);
        return userMapper.toDto(user);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public UserDto create(User resources) {
        resources.setStatus(1);
        Set<Role> roles = resources.getRoles();
        return userMapper.toDto(userRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        ValidationUtil.isNull( user.getId(),"User","id",resources.getId());
        user.copy(resources);
        userRepository.save(user);
    }


    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<UserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserDto user : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("账户名(学号)", user.getUsername());
            map.put("用户状态(1: 正常 2: 删除)", user.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String newPassword) {
        userRepository.updatePass(username, newPassword, new Date());
    }

    @Override
    public Map<String, Long> getCount() {
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("user", userRepository.count());
        return resultMap;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateCenter(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        if (resources.getName() != null) user.setName(resources.getName());
        if (resources.getPhone() != null) user.setPhone(resources.getPhone());
        if (resources.getQq() != null) user.setQq(resources.getQq());
        if (resources.getEmail() != null) user.setEmail(resources.getEmail());
        if (resources.getCollege() != null) user.setCollege(resources.getCollege());
        if (resources.getComeYear() != null) user.setComeYear(resources.getComeYear());
        if (resources.getGrade() != null) user.setGrade(resources.getGrade());
        if (resources.getMajor() != null) user.setMajor(resources.getMajor());
        if (resources.getOffice() != null) user.setOffice(resources.getOffice());
        userRepository.save(user);
    }
}
