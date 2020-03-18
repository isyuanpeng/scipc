package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.common.exception.BadRequestException;
import com.yuanpeng.common.utils.SecurityUtils;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.UserQueryCriteria;
import com.yuanpeng.security.service.OnlineUserService;
import com.yuanpeng.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-16 15:54
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OnlineUserService onlineUserService;


    public UserController(UserService userService, PasswordEncoder passwordEncoder, OnlineUserService onlineUserService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.onlineUserService = onlineUserService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('user:list')")
    public void download(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
        userService.download(userService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询用户")
    @ApiOperation("查询用户")
    @PreAuthorize("@el.check('user:list')")
    public ResponseEntity<Object> getUsers(UserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(userService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增用户")
    @ApiOperation("新增用户")
    @PreAuthorize("@el.check('user:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody User resources){
        resources.setPassword(passwordEncoder.encode("123456"));
        return new ResponseEntity<>(userService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改用户")
    @ApiOperation("修改用户")
    @PreAuthorize("@el.check('user:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody User resources){
        userService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除用户")
    @ApiOperation("删除用户")
    @PreAuthorize("@el.check('user:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAll(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/role/{code}")
    @Log("查询用户根据角色")
    @ApiOperation("查询用户根据角色")
    public ResponseEntity<Object> getUsersByRole(@PathVariable String code, Pageable pageable){
        return new ResponseEntity<>(userService.queryAllByRoleCode(code, pageable),HttpStatus.OK);
    }

    @Log("修改用户：个人中心")
    @ApiOperation("修改用户：个人中心")
    @PutMapping(value = "/center")
    public ResponseEntity<Object> center(@Validated(User.Update.class) @RequestBody User resources){
        UserDto userDto = userService.findByUsername(SecurityUtils.getUsername());
        if(!resources.getId().equals(userDto.getId())){
            throw new BadRequestException("不能修改他人资料");
        }
        userService.updateCenter(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}