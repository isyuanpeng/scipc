package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Violation;
import com.yuanpeng.querycriteria.ViolationQueryCriteria;
import com.yuanpeng.service.ViolationService;
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

@Api(tags = " 违规记录管理")
@RestController
@RequestMapping("/api/violations")
public class ViolationController {

    private final ViolationService violationService;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('violation:list')")
    public void download(HttpServletResponse response, ViolationQueryCriteria criteria) throws IOException {
        violationService.download(violationService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询 违规记录")
    @ApiOperation("查询 违规记录")
    @PreAuthorize("@el.check('violation:list')")
    public ResponseEntity<Object> getViolations(ViolationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(violationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增 违规记录")
    @ApiOperation("新增 违规记录")
    @PreAuthorize("@el.check('violation:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Violation resources){
        return new ResponseEntity<>(violationService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改 违规记录")
    @ApiOperation("修改 违规记录")
    @PreAuthorize("@el.check('violation:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Violation resources){
        violationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除 违规记录")
    @ApiOperation("删除 违规记录")
    @PreAuthorize("@el.check('violation:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        violationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}