package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.entity.Message;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.querycriteria.MessageQueryCriteria;
import com.yuanpeng.querycriteria.PermissionQueryCriteria;
import com.yuanpeng.service.MessageService;
import com.yuanpeng.service.mapper.MessageMapper;
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

@Api(tags = "系统消息")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Log("导出消息")
    @ApiOperation("导出消息")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('message:list')")
    public void download(HttpServletResponse response, MessageQueryCriteria criteria) throws IOException {
        messageService.download(messageService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询消息")
    @ApiOperation("查询消息")
    public ResponseEntity<Object> getPermissions(MessageQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(messageService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @GetMapping("/pull")
    @Log("拉取推送")
    @ApiOperation("拉取推送")
    public ResponseEntity<Object> pull(){
        return new ResponseEntity<>(messageService.pull(), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增消息")
    @ApiOperation("新增消息")
    @PreAuthorize("@el.check('message:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Message resources){
        return new ResponseEntity<>(messageService.create(resources),HttpStatus.CREATED);
    }

    @PostMapping("/student")
    @Log("推送学生")
    @ApiOperation("推送学生")
    @PreAuthorize("@el.check('message:add')")
    public ResponseEntity<Object> pushToStudent(@Validated @RequestBody Message resources){
        messageService.pushToStudent(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改消息")
    @ApiOperation("修改消息")
    @PreAuthorize("@el.check('message:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Message resources){
        messageService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除消息")
    @ApiOperation("删除消息")
    @PreAuthorize("@el.check('message:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
