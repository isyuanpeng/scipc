package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.querycriteria.PermissionQueryCriteria;
import com.yuanpeng.service.PermissionService;
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
 * @create: 2020-02-18 11:56
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('permission:list')")
    public void download(HttpServletResponse response, PermissionQueryCriteria criteria) throws IOException {
        permissionService.download(permissionService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询权限")
    @ApiOperation("查询权限")
    @PreAuthorize("@el.check('permission:list')")
    public ResponseEntity<Object> getPermissions(PermissionQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(permissionService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增权限")
    @ApiOperation("新增权限")
    @PreAuthorize("@el.check('permission:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Permission resources){
        return new ResponseEntity<>(permissionService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改权限")
    @ApiOperation("修改权限")
    @PreAuthorize("@el.check('permission:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Permission resources){
        permissionService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除权限")
    @ApiOperation("删除权限")
    @PreAuthorize("@el.check('permission:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}