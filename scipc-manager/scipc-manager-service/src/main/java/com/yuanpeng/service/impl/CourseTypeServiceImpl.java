package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.FileUtil;
import com.yuanpeng.common.utils.PageUtil;
import com.yuanpeng.common.utils.QueryHelp;
import com.yuanpeng.common.utils.ValidationUtil;
import com.yuanpeng.dto.CourseTypeDto;
import com.yuanpeng.entity.CourseType;
import com.yuanpeng.querycriteria.CourseTypeQueryCriteria;
import com.yuanpeng.repository.CourseTypeRepository;
import com.yuanpeng.service.CourseTypeService;
import com.yuanpeng.service.mapper.CourseTypeMapper;
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
 * @create: 2020-02-23 23:30
 */
@Service
//@CacheConfig(cacheNames = "courseType")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseTypeServiceImpl implements CourseTypeService {

    private final CourseTypeRepository courseTypeRepository;

    private final CourseTypeMapper courseTypeMapper;

    public CourseTypeServiceImpl(CourseTypeRepository courseTypeRepository, CourseTypeMapper courseTypeMapper) {
        this.courseTypeRepository = courseTypeRepository;
        this.courseTypeMapper = courseTypeMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(CourseTypeQueryCriteria criteria, Pageable pageable){
        Page<CourseType> page = courseTypeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(courseTypeMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<CourseTypeDto> queryAll(CourseTypeQueryCriteria criteria){
        return courseTypeMapper.toDto(courseTypeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public CourseTypeDto findById(Long id) {
        CourseType courseType = courseTypeRepository.findById(id).orElseGet(CourseType::new);
        ValidationUtil.isNull(courseType.getId(),"CourseType","id",id);
        return courseTypeMapper.toDto(courseType);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public CourseTypeDto create(CourseType resources) {
        return courseTypeMapper.toDto(courseTypeRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(CourseType resources) {
        CourseType courseType = courseTypeRepository.findById(resources.getId()).orElseGet(CourseType::new);
        ValidationUtil.isNull( courseType.getId(),"CourseType","id",resources.getId());
        courseType.copy(resources);
        courseTypeRepository.save(courseType);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            courseTypeRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CourseTypeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CourseTypeDto courseType : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("课程类型名称", courseType.getName());
            map.put("课程类型描述", courseType.getRemark());
            map.put("创建时间", courseType.getCreateTime());
            map.put("最后一次修改时间", courseType.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        courseTypeRepository.deleteById(id);
    }
}