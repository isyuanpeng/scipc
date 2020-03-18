package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.querycriteria.StudentCourseQueryCriteria;
import com.yuanpeng.service.StudentCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 09:15
 */
@Api(tags = "学生课程管理")
@RestController
@RequestMapping("/api/student-courses")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('studentCourse:list')")
    public void download(HttpServletResponse response, StudentCourseQueryCriteria criteria) throws IOException {
        studentCourseService.download(studentCourseService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询学生课程")
    @ApiOperation("查询学生课程")
    @PreAuthorize("@el.check('studentCourse:list')")
    public ResponseEntity<Object> getStudentCourses(StudentCourseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(studentCourseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增学生课程")
    @ApiOperation("新增学生课程")
    @PreAuthorize("@el.check('studentCourse:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody StudentCourse resources){
        boolean flag = studentCourseService.isExist(resources.getCourse().getId(), resources.getStudent().getId());
        if (flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(studentCourseService.create(resources), HttpStatus.CREATED);
        }
    }

    @PutMapping
    @Log("修改学生课程")
    @ApiOperation("修改学生课程")
    @PreAuthorize("@el.check('studentCourse:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody StudentCourse resources){
        studentCourseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除学生课程")
    @ApiOperation("删除学生课程")
    @PreAuthorize("@el.check('studentCourse:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        studentCourseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}