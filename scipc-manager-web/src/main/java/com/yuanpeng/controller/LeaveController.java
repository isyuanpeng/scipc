package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Leave;
import com.yuanpeng.querycriteria.LeaveQueryCriteria;
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

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 10:02
 */
@Api(tags = "请假管理")
@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('leave:list')")
    public void download(HttpServletResponse response, LeaveQueryCriteria criteria) throws IOException {
        leaveService.download(leaveService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询请假")
    @ApiOperation("查询请假")
    @PreAuthorize("@el.check('leave:list')")
    public ResponseEntity<Object> getLeaves(LeaveQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(leaveService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增请假")
    @ApiOperation("新增请假")
    @PreAuthorize("@el.check('leave:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Leave resources){
        return new ResponseEntity<>(leaveService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改请假")
    @ApiOperation("修改请假")
    @PreAuthorize("@el.check('leave:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Leave resources){
        leaveService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除请假")
    @ApiOperation("删除请假")
    @PreAuthorize("@el.check('leave:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAll(@PathVariable Long id) {
        leaveService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}