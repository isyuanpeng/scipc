package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.*;
import com.yuanpeng.dto.ViolationDto;
import com.yuanpeng.entity.User;
import com.yuanpeng.entity.Violation;
import com.yuanpeng.querycriteria.ViolationQueryCriteria;
import com.yuanpeng.repository.UserRepository;
import com.yuanpeng.repository.ViolationRepository;
import com.yuanpeng.service.ViolationService;
import com.yuanpeng.service.mapper.ViolationMapper;
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

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ViolationServiceImpl implements ViolationService {
    private final ViolationRepository violationRepository;

    private final ViolationMapper violationMapper;

    private final UserRepository userRepository;

    public ViolationServiceImpl(ViolationRepository violationRepository, ViolationMapper violationMapper, UserRepository userRepository) {
        this.violationRepository = violationRepository;
        this.violationMapper = violationMapper;
        this.userRepository = userRepository;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(ViolationQueryCriteria criteria, Pageable pageable){
        Page<Violation> page = violationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(violationMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<ViolationDto> queryAll(ViolationQueryCriteria criteria){
        return violationMapper.toDto(violationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public ViolationDto findById(Long id) {
        Violation violation = violationRepository.findById(id).orElseGet(Violation::new);
        ValidationUtil.isNull(violation.getId(),"Violation","id",id);
        return violationMapper.toDto(violation);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public ViolationDto create(Violation resources) {
        User currentUser = userRepository.findByUsername(SecurityUtils.getUsername());
        User student = userRepository.findByUsername(resources.getStudent().getUsername());
        resources.setStudent(student);
        userRepository.updateViolationCount(student.getId());
        resources.setTeacher(currentUser);
        return violationMapper.toDto(violationRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Violation resources) {
        Violation violation = violationRepository.findById(resources.getId()).orElseGet(Violation::new);
        ValidationUtil.isNull( violation.getId(),"Violation","id",resources.getId());
        violation.copy(resources);
        violationRepository.save(violation);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            violationRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ViolationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ViolationDto violation : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("违规学生", violation.getStudent().getName());
            map.put("违规原因", violation.getReason());
            map.put("创建老师", violation.getTeacher().getName());
            map.put("创建时间", violation.getCreateTime());
            map.put("最后一次修改时间", violation.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        violationRepository.deleteById(id);
    }

}
