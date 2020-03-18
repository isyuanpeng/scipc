package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Sign;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.SignQueryCriteria;
import com.yuanpeng.security.config.AnonymousAccess;
import com.yuanpeng.service.SignService;
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
 * @create: 2020-02-25 10:22
 */
@Api(tags = "签到管理")
@RestController
@RequestMapping("/api/signs")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService) {
        this.signService = signService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('sign:list')")
    public void download(HttpServletResponse response, SignQueryCriteria criteria) throws IOException {
        signService.download(signService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询签到")
    @ApiOperation("查询签到")
    @PreAuthorize("@el.check('sign:list')")
    public ResponseEntity<Object> getSigns(SignQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(signService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增签到")
    @ApiOperation("新增签到")
    @PreAuthorize("@el.check('sign:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Sign resources){
        return new ResponseEntity<>(signService.create(resources), HttpStatus.CREATED);
    }

    @GetMapping("/in")
    @Log("签到")
    @ApiOperation("签到")
    @AnonymousAccess
    public ResponseEntity<Object> signIn(){
        return new ResponseEntity<>(signService.signIn(), HttpStatus.CREATED);
    }

    @PutMapping("/out")
    @Log("签退")
    @ApiOperation("签退")
    @AnonymousAccess
    public ResponseEntity<Object> signOut(@RequestBody Sign resources){
        return new ResponseEntity<>(signService.signOut(resources), HttpStatus.CREATED);
    }


    @PutMapping
    @Log("修改签到")
    @ApiOperation("修改签到")
    @PreAuthorize("@el.check('sign:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Sign resources){
        signService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除签到")
    @ApiOperation("删除签到")
    @PreAuthorize("@el.check('sign:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        signService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}