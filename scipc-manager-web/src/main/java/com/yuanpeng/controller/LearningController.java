package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Learning;
import com.yuanpeng.entity.Leave;
import com.yuanpeng.querycriteria.LearningQueryCriteria;
import com.yuanpeng.querycriteria.LeaveQueryCriteria;
import com.yuanpeng.service.LearningService;
import com.yuanpeng.service.LeaveService;
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

@Api(tags = "学习记录管理")
@RestController
@RequestMapping("/api/learnings")
public class LearningController {

    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('leave:list')")
    public void download(HttpServletResponse response, LearningQueryCriteria criteria) throws IOException {
        learningService.download(learningService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询请假")
    @ApiOperation("查询请假")
    @PreAuthorize("@el.check('leave:list')")
    public ResponseEntity<Object> getLeaves(LearningQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(learningService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增请假")
    @ApiOperation("新增请假")
    @PreAuthorize("@el.check('leave:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Learning resources){
        return new ResponseEntity<>(learningService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改请假")
    @ApiOperation("修改请假")
    @PreAuthorize("@el.check('leave:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Learning resources){
        learningService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除请假")
    @ApiOperation("删除请假")
    @PreAuthorize("@el.check('leave:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        learningService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}