package com.yuanpeng.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.yuanpeng.common.exception.BadRequestException;
import com.yuanpeng.common.utils.*;
import com.yuanpeng.dto.HomeworkDto;
import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.*;
import com.yuanpeng.querycriteria.HomeworkQueryCriteria;
import com.yuanpeng.repository.*;
import com.yuanpeng.service.HomeworkService;
import com.yuanpeng.service.StudentCourseService;
import com.yuanpeng.service.mapper.HomeworkMapper;
import com.yuanpeng.service.mapper.LocalFileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 09:33
 */
@Service
//@CacheConfig(cacheNames = "homework")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class HomeworkServiceImpl implements HomeworkService {

    @Value("${file.path}")
    private String path;

    @Value("${file.maxSize}")
    private long maxSize;

    private final HomeworkRepository homeworkRepository;

    private final HomeworkMapper homeworkMapper;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final HomeworkScoreRepository homeworkScoreRepository;

    private final StudentCourseRepository studentCourseRepository;

    private final LocalFileRepository localFileRepository;

    private final LocalFileMapper localFileMapper;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, HomeworkMapper homeworkMapper, UserRepository userRepository, CourseRepository courseRepository, StudentCourseService studentCourseService, StudentCourseRepository studentCourseService1, HomeworkScoreRepository homeworkScoreRepository, StudentCourseRepository studentCourseRepository, LocalFileRepository localFileRepository, LocalFileMapper localFileMapper) {
        this.homeworkRepository = homeworkRepository;
        this.homeworkMapper = homeworkMapper;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.homeworkScoreRepository = homeworkScoreRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.localFileRepository = localFileRepository;
        this.localFileMapper = localFileMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(HomeworkQueryCriteria criteria, Pageable pageable){
        Page<Homework> page = homeworkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(homeworkMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<HomeworkDto> queryAll(HomeworkQueryCriteria criteria){
        return homeworkMapper.toDto(homeworkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public HomeworkDto findById(Long id) {
        Homework homework = homeworkRepository.findById(id).orElseGet(Homework::new);
        ValidationUtil.isNull(homework.getId(),"Homework","id",id);
        return homeworkMapper.toDto(homework);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public HomeworkDto create(Map<String, Object> map, MultipartFile file) {
        Homework homework = new Homework();
        LocalFile localFile;
        String baseName = (String) map.get("name");
        homework.setRemark((String) map.get("remark"));
        homework.setCourseChapter((String) map.get("courseChapter"));
        homework.setIsRead(0);

        User student = userRepository.findById((Long) map.get("studentId")).get();
        Course course = courseRepository.findById((Long) map.get("courseId")).get();
        homework.setStudent(student);
        homework.setCourse(course);

        // 获得后缀
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());

        // 构造一个名字 并且创建LocalFile
        String homeworkName = student.getName() + "_" + student.getMajor() + "_"
                + course.getName() + "_" + course.getChapterCount() + "_" + baseName + suffix;

        // 文件的上传 包括文件的改名
        FileUtil.checkSize(maxSize, file.getSize());
        String type = "作业";
        File realFile = FileUtil.upload(file, path + type +  File.separator, homeworkName);
        if(ObjectUtil.isNull(realFile)){
            throw new BadRequestException("上传失败");
        }
        try {
            LocalFile localStorage = new LocalFile(
                    realFile.getName(),
                    suffix,
                    realFile.getPath(),
                    type,
                    FileUtil.getSize(file.getSize()),
                    SecurityUtils.getUsername()
            );
            localFile = localFileRepository.save(localStorage);
        } catch (Exception e){
            FileUtil.del(realFile);
            throw e;
        }

        // 完善作业的信息
        homework.setFile(localFile);
        homework.setName(homeworkName);

        // 提交一个作业后就生成一条作业分数的记录
        HomeworkScore homeworkScore = new HomeworkScore();
        homeworkScore.setHomework(homework);
        homeworkScoreRepository.save(homeworkScore);

        // 上传一个作业后 该学生课程进度+1 如果进度 == 课程章节数 完成该课程
        studentCourseRepository.updateProgress(course.getId(), student.getId());
        StudentCourse studentCourse = studentCourseRepository.findByCourseAndStudent(course.getId(), student.getId());
        if (studentCourse.getProgress().equals(course.getChapterCount())) {
            studentCourseRepository.updateAchieve(course.getId(), student.getId());
        }
        return homeworkMapper.toDto(homeworkRepository.save(homework));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Homework resources) {
        Homework homework = homeworkRepository.findById(resources.getId()).orElseGet(Homework::new);
        ValidationUtil.isNull( homework.getId(),"Homework","id",resources.getId());
        homework.copy(resources);
        homeworkRepository.save(homework);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            homeworkRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<HomeworkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (HomeworkDto homework : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("作业名", homework.getName());
            map.put("课程id", homework.getCourse());
            map.put("作业属于课程的第n章", homework.getCourseChapter());
            map.put("学生id", homework.getStudent());
            map.put("作业描述", homework.getRemark());
            map.put("文件位置", homework.getFile());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void delete(Long id) {
        homeworkRepository.deleteById(id);
    }

    @Override
    public void correct(Long homeworkId, String score, String response, MultipartFile file) {
        Homework homework = homeworkRepository.findById(homeworkId).orElseGet(Homework::new);
        homework.setScore(score);
        homework.setResponseContent(response);
        User teacher = userRepository.findByUsername(SecurityUtils.getUsername());
        homework.setTeacher(teacher);
        homework.setIsRead(1);

        // 学生上传的作业
        LocalFile originFile = homework.getFile();
        String resFileName = "回复_" + homework.getName();
        // 文件的上传 包括文件的改名
        FileUtil.checkSize(maxSize, file.getSize());
        String type = "作业回复";
        File responseFile = FileUtil.upload(file, path + type +  File.separator, resFileName);
        // 保存后的回复文件
        LocalFile saveResFile;
        if(ObjectUtil.isNull(responseFile)){
            throw new BadRequestException("上传失败");
        }
        try {
            LocalFile resLocalFile = new LocalFile(
                    responseFile.getName(),
                    originFile.getSuffix(),
                    responseFile.getPath(),
                    type,
                    FileUtil.getSize(file.getSize()),
                    SecurityUtils.getUsername()
            );
            saveResFile = localFileRepository.save(resLocalFile);
        } catch (Exception e){
            FileUtil.del(responseFile);
            throw e;
        }
        homework.setResponseFile(saveResFile);
        homeworkRepository.save(homework);
    }

    @Override
    public void allot(Long homeworkId, Long userId) {
        Homework homework = homeworkRepository.findById(homeworkId).orElseGet(Homework::new);
        User user = userRepository.findById(userId).get();
        User alloter = userRepository.findByUsername(SecurityUtils.getUsername());
        homework.setTeacher(user);
        homework.setAlloter(alloter.getName());
        homeworkRepository.save(homework);
    }
}