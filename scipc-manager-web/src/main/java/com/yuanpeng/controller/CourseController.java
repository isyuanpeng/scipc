package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Course;
import com.yuanpeng.querycriteria.CourseQueryCriteria;
import com.yuanpeng.service.CourseService;
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
 * @create: 2020-02-24 00:07
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('course:list')")
    public void download(HttpServletResponse response, CourseQueryCriteria criteria) throws IOException {
        courseService.download(courseService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询课程")
    @ApiOperation("查询课程")
    @PreAuthorize("@el.check('course:list')")
    public ResponseEntity<Object> getCourses(CourseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(courseService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程")
    @ApiOperation("新增课程")
    @PreAuthorize("@el.check('course:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Course resources){
        return new ResponseEntity<>(courseService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程")
    @ApiOperation("修改课程")
    @PreAuthorize("@el.check('course:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Course resources){
        courseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除课程")
    @ApiOperation("删除课程")
    @PreAuthorize("@el.check('course:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAll(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
