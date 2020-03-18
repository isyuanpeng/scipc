package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.*;
import com.yuanpeng.dto.SignDto;
import com.yuanpeng.entity.Sign;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.SignQueryCriteria;
import com.yuanpeng.repository.SignRepository;
import com.yuanpeng.repository.UserRepository;
import com.yuanpeng.service.SignService;
import com.yuanpeng.service.mapper.SignMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 10:20
 */
@Service
//@CacheConfig(cacheNames = "sign")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SignServiceImpl implements SignService {

    private final SignRepository signRepository;

    private final SignMapper signMapper;

    private final UserRepository userRepository;

    public SignServiceImpl(SignRepository signRepository, SignMapper signMapper, UserRepository userRepository) {
        this.signRepository = signRepository;
        this.signMapper = signMapper;
        this.userRepository = userRepository;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(SignQueryCriteria criteria, Pageable pageable){
        Page<Sign> page = signRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(signMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<SignDto> queryAll(SignQueryCriteria criteria){
        return signMapper.toDto(signRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public SignDto findById(Long id) {
        Sign sign = signRepository.findById(id).orElseGet(Sign::new);
        ValidationUtil.isNull(sign.getId(),"Sign","id",id);
        return signMapper.toDto(sign);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public SignDto create(Sign resources) {
        return signMapper.toDto(signRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Sign resources) {
        Sign sign = signRepository.findById(resources.getId()).orElseGet(Sign::new);
        ValidationUtil.isNull( sign.getId(),"Sign","id",resources.getId());
        sign.copy(resources);
        signRepository.save(sign);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            signRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<SignDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SignDto sign : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("学生", sign.getStudent().getName());
            map.put("开始时间", sign.getStartTime());
            map.put("结束时间", sign.getEndTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        signRepository.deleteById(id);
    }

    @Override
    public SignDto signIn() {
        User user = userRepository.findByUsername(SecurityUtils.getUsername());
        Sign sign = new Sign();
        sign.setStudent(user);
        sign.setStartTime(new Timestamp(new Date().getTime()));
        return signMapper.toDto(signRepository.save(sign));
    }

    @Override
    public SignDto signOut(Sign resources) {
        Sign sign = signRepository.findById(resources.getId()).orElseGet(Sign::new);
        sign.setEndTime(new Timestamp(new Date().getTime()));
        Duration duration = Duration.between(sign.getStartTime().toLocalDateTime(), sign.getEndTime().toLocalDateTime());
        long diffInMinutes = duration.toMinutes();
        sign.setDifference(diffInMinutes);

        // 更新一下学习时长的记录
        User student = userRepository.findById(resources.getStudent().getId()).get();
        long weekTime = student.getWeekTime() == null ? 0 : student.getWeekTime();
        student.setWeekTime(weekTime + diffInMinutes);
        long learningTime = student.getLearningTime() == null ? 0 : student.getWeekTime();
        student.setLearningTime(learningTime + diffInMinutes);
        userRepository.save(student);

        return signMapper.toDto(signRepository.save(sign));
    }
}