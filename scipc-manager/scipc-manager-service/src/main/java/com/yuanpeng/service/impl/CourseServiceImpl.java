package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.FileUtil;
import com.yuanpeng.common.utils.PageUtil;
import com.yuanpeng.common.utils.QueryHelp;
import com.yuanpeng.common.utils.ValidationUtil;
import com.yuanpeng.dto.CourseDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.querycriteria.CourseQueryCriteria;
import com.yuanpeng.repository.CourseRepository;
import com.yuanpeng.service.CourseService;
import com.yuanpeng.service.mapper.CourseMapper;
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
 * @create: 2020-02-23 23:17
 */
@Service
//@CacheConfig(cacheNames = "course")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(CourseQueryCriteria criteria, Pageable pageable){
        Page<Course> page = courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(courseMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<CourseDto> queryAll(CourseQueryCriteria criteria){
        return courseMapper.toDto(courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public CourseDto findById(Long id) {
        Course course = courseRepository.findById(id).orElseGet(Course::new);
        ValidationUtil.isNull(course.getId(),"Course","id",id);
        return courseMapper.toDto(course);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public CourseDto create(Course resources) {
        return courseMapper.toDto(courseRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Course resources) {
        Course course = courseRepository.findById(resources.getId()).orElseGet(Course::new);
        ValidationUtil.isNull( course.getId(),"Course","id",resources.getId());
        course.copy(resources);
        courseRepository.save(course);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            courseRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CourseDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CourseDto course : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("课程代码", course.getCode());
            map.put("课程名称", course.getName());
            map.put("课程章节数", course.getChapterCount());
            map.put("网址", course.getUrl());
            map.put("学分", course.getCredit());
            map.put("比选修(1: 必修 0: 选修)", course.getIsRequired());
            map.put("课程描述", course.getRemark());
            map.put("课程类型id", course.getCourseType());
            map.put("课程状态(1: 正常 0: 删除)", course.getStatus());
            map.put("创建时间", course.getCreateTime());
            map.put("最后一次修改时间", course.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}