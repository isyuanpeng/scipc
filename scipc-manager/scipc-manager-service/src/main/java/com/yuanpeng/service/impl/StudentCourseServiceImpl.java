package com.yuanpeng.service.impl;

import com.yuanpeng.common.utils.*;
import com.yuanpeng.dto.StudentCourseDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.StudentCourseQueryCriteria;
import com.yuanpeng.repository.StudentCourseRepository;
import com.yuanpeng.repository.UserRepository;
import com.yuanpeng.service.StudentCourseService;
import com.yuanpeng.service.mapper.StudentCourseMapper;
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
 * @create: 2020-03-05 22:37
 */
@Service
//@CacheConfig(cacheNames = "studentCourse")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;

    private final StudentCourseMapper studentCourseMapper;

    private final UserRepository userRepository;

    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository, StudentCourseMapper studentCourseMapper, UserRepository userRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentCourseMapper = studentCourseMapper;
        this.userRepository = userRepository;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(StudentCourseQueryCriteria criteria, Pageable pageable){
        Page<StudentCourse> page = studentCourseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(studentCourseMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<StudentCourseDto> queryAll(StudentCourseQueryCriteria criteria){
        return studentCourseMapper.toDto(studentCourseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public StudentCourseDto findById(Long id) {
        StudentCourse studentCourse = studentCourseRepository.findById(id).orElseGet(StudentCourse::new);
        ValidationUtil.isNull(studentCourse.getId(),"StudentCourse","id",id);
        return studentCourseMapper.toDto(studentCourse);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public StudentCourseDto create(StudentCourse resources) {
        return studentCourseMapper.toDto(studentCourseRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(StudentCourse resources) {
        StudentCourse studentCourse = studentCourseRepository.findById(resources.getId()).orElseGet(StudentCourse::new);
        ValidationUtil.isNull( studentCourse.getId(),"StudentCourse","id",resources.getId());
        studentCourse.copy(resources);
        studentCourseRepository.save(studentCourse);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            studentCourseRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<StudentCourseDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (StudentCourseDto studentCourse : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("课程id", studentCourse.getCourse().getName());
            map.put("学生id", studentCourse.getStudent().getName());
            map.put("是否完成(0: 未完成 1:wjig )", studentCourse.getIsAchieve());
            map.put("进度 也就是当前学到第几章", studentCourse.getProgress());
            map.put("创建时间", studentCourse.getCreateTime());
            map.put("最后一次修改时间", studentCourse.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        studentCourseRepository.deleteById(id);
    }

    @Override
    public void correct(StudentCourse resources) {
        StudentCourse studentCourse = studentCourseRepository.findById(resources.getId()).orElseGet(StudentCourse::new);
        ValidationUtil.isNull( studentCourse.getId(),"StudentCourse","id",resources.getId());

        // 学生的总学分也更新
        User user = userRepository.findByUsername(SecurityUtils.getUsername());
        user.setCredit(user.getCredit() + studentCourse.getCourse().getCredit());
        userRepository.save(user);

        studentCourse.setTeacher(user);
        studentCourse.setScore(resources.getScore());
        studentCourse.setIsAchieve(1);
        studentCourseRepository.save(studentCourse);
    }

    @Override
    public void updateProgress(Course course, User user) {
        studentCourseRepository.updateProgress(course.getId(), user.getId());
    }

    @Override
    public Boolean isExist(Long courseId, Long studentId) {
        return studentCourseRepository.existsByCourseIdAndStudentId(courseId, studentId);
    }


}