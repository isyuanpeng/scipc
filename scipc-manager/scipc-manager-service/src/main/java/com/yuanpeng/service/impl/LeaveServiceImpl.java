package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.*;
import com.yuanpeng.dto.LeaveDto;
import com.yuanpeng.entity.Leave;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.LeaveQueryCriteria;
import com.yuanpeng.repository.LeaveRepository;
import com.yuanpeng.repository.UserRepository;
import com.yuanpeng.service.LeaveService;
import com.yuanpeng.service.mapper.LeaveMapper;
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
 * @create: 2020-02-25 10:01
 */
@Service
//@CacheConfig(cacheNames = "leave")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;

    private final LeaveMapper leaveMapper;

    private final UserRepository userRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository, LeaveMapper leaveMapper, UserRepository userRepository) {
        this.leaveRepository = leaveRepository;
        this.leaveMapper = leaveMapper;
        this.userRepository = userRepository;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(LeaveQueryCriteria criteria, Pageable pageable){
        Page<Leave> page = leaveRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(leaveMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<LeaveDto> queryAll(LeaveQueryCriteria criteria){
        return leaveMapper.toDto(leaveRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public LeaveDto findById(Long id) {
        Leave leave = leaveRepository.findById(id).orElseGet(Leave::new);
        ValidationUtil.isNull(leave.getId(),"Leave","id",id);
        return leaveMapper.toDto(leave);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public LeaveDto create(Leave resources) {
        return leaveMapper.toDto(leaveRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Leave resources) {
        User currentUser = userRepository.findByUsername(SecurityUtils.getUsername());
        Leave leave = leaveRepository.findById(resources.getId()).orElseGet(Leave::new);
        ValidationUtil.isNull( leave.getId(),"Leave","id",resources.getId());
        leave.copy(resources);
        leave.setIsRead(1);
        leave.setIsApprove(1);
        leave.setTeacher(currentUser);
        leaveRepository.save(leave);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            leaveRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<LeaveDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LeaveDto leave : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("请假时间", leave.getDatetime());
            map.put("请假原因", leave.getReason());
            map.put(" studentId",  leave.getStudent().getName());
            map.put("是否已读", leave.getIsRead());
            map.put("是否批准", leave.getIsApprove());
            map.put("老师", leave.getTeacher().getName());
            map.put("回复内容", leave.getResContent());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        leaveRepository.deleteById(id);
    }
}