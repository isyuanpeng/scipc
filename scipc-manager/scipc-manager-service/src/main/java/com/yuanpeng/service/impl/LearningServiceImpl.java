package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.FileUtil;
import com.yuanpeng.common.utils.PageUtil;
import com.yuanpeng.common.utils.QueryHelp;
import com.yuanpeng.common.utils.ValidationUtil;
import com.yuanpeng.dto.LearningDto;
import com.yuanpeng.dto.LeaveDto;
import com.yuanpeng.entity.Learning;
import com.yuanpeng.entity.Leave;
import com.yuanpeng.entity.LocalFile;
import com.yuanpeng.querycriteria.LearningQueryCriteria;
import com.yuanpeng.repository.LearningRepository;
import com.yuanpeng.service.LearningService;
import com.yuanpeng.service.mapper.LearningMapper;
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
public class LearningServiceImpl implements LearningService {

    private final LearningRepository learningRepository;

    private final LearningMapper learningMapper;

    public LearningServiceImpl(LearningRepository learningRepository, LearningMapper learningMapper) {
        this.learningRepository = learningRepository;
        this.learningMapper = learningMapper;
    }

    @Override
    public Map<String, Object> queryAll(LearningQueryCriteria criteria, Pageable pageable) {
        Page<Learning> page = learningRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(learningMapper::toDto));
    }

    @Override
    public List<LearningDto> queryAll(LearningQueryCriteria criteria) {
        return learningMapper.toDto(learningRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public LearningDto findById(Long id) {
        Learning Learning = learningRepository.findById(id).orElseGet(Learning::new);
        ValidationUtil.isNull(Learning.getId(),"Leave","id",id);
        return learningMapper.toDto(Learning);
    }

    @Override
    public LearningDto create(Learning resources) {
        return learningMapper.toDto(learningRepository.save(resources));
    }

    @Override
    public void update(Learning resources) {
        Learning learning = learningRepository.findById(resources.getId()).orElseGet(Learning::new);
        ValidationUtil.isNull( learning.getId(),"Leave","id",resources.getId());
        learning.copy(resources);
        learningRepository.save(learning);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            learningRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<LearningDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LearningDto learning : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("学生", learning.getStudent().getName());
            map.put("学习时长", learning.getDuration());
            map.put("学习时长类型",  learning.getType() == 1 ? "日学习时长" : "周学习时长");
            map.put("创建时间", learning.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        learningRepository.deleteById(id);
    }
}
