package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.CourseType;
import com.yuanpeng.querycriteria.CourseTypeQueryCriteria;
import com.yuanpeng.service.CourseTypeService;
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
 * @create: 2020-02-23 23:30
 */
@Api(tags = "课程类型管理")
@RestController
@RequestMapping("/api/course-types")
public class CourseTypeController {

    private final CourseTypeService courseTypeService;

    public CourseTypeController(CourseTypeService courseTypeService) {
        this.courseTypeService = courseTypeService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('courseType:list')")
    public void download(HttpServletResponse response, CourseTypeQueryCriteria criteria) throws IOException {
        courseTypeService.download(courseTypeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询课程类型")
    @ApiOperation("查询课程类型")
    @PreAuthorize("@el.check('courseType:list')")
    public ResponseEntity<Object> getCourseTypes(CourseTypeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(courseTypeService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程类型")
    @ApiOperation("新增课程类型")
    @PreAuthorize("@el.check('courseType:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody CourseType resources){
        return new ResponseEntity<>(courseTypeService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程类型")
    @ApiOperation("修改课程类型")
    @PreAuthorize("@el.check('courseType:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody CourseType resources){
        courseTypeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除课程类型")
    @ApiOperation("删除课程类型")
    @PreAuthorize("@el.check('courseType:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        courseTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}