package com.yuanpeng.security.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.security.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-19 17:50
 */
@RestController
@RequestMapping("/auth/online")
@Api(tags = "系统：在线用户管理")
public class OnlineUserController {

    private final OnlineUserService onlineUserService;

    public OnlineUserController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @ApiOperation("查询在线用户")
    @GetMapping
    @PreAuthorize("@el.check('online:list')")
    public ResponseEntity<Object> getAll(String filter, Pageable pageable){
        return new ResponseEntity<>(onlineUserService.getAll(filter, pageable),HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('online:list')")
    public void download(HttpServletResponse response, String filter) throws IOException {
        onlineUserService.download(onlineUserService.getAll(filter), response);
    }

    @ApiOperation("踢出用户")
    @DeleteMapping
    @PreAuthorize("@el.check('online:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<String> keys) throws Exception {
        for (String key : keys) {
            onlineUserService.kickOut(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
