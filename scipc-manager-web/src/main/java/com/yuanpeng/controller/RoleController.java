package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Role;
import com.yuanpeng.querycriteria.RoleQueryCriteria;
import com.yuanpeng.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: RoleController
 * @author: YuanPeng
 * @create: 2020-02-15 22:41
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('role:list')")
    public void download(HttpServletResponse response, RoleQueryCriteria criteria) throws IOException {
        roleService.download(roleService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询角色")
    @ApiOperation("查询角色")
    @PreAuthorize("@el.check('role:list')")
    public ResponseEntity<Object> getRoles(RoleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(roleService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增角色")
    @ApiOperation("新增角色")
    @PreAuthorize("@el.check('role:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Role resources){
        return new ResponseEntity<>(roleService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改角色")
    @ApiOperation("修改角色")
    @PreAuthorize("@el.check('role:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Role resources){
        roleService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除角色")
    @ApiOperation("删除角色")
    @PreAuthorize("@el.check('role:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAll(@PathVariable Long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("角色的用户数量")
    @ApiOperation("角色的用户数量")
    @PreAuthorize(("@el.check('role:list')"))
    @GetMapping("/count")
    public ResponseEntity<Object> getCount() {
        Map<String, Integer> resultMap = new HashMap<>(roleService.count());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
