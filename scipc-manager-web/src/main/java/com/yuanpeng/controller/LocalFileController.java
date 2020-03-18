package com.yuanpeng.controller;


import com.yuanpeng.aop.Log;
import com.yuanpeng.common.exception.GlobalException;
import com.yuanpeng.common.utils.FileUtil;
import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.LocalFile;
import com.yuanpeng.querycriteria.LocalFileQueryCriteria;
import com.yuanpeng.service.LocalFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-07 17:50
 */
@Api(tags = "工具：本地存储管理")
@RestController
@RequestMapping("/api/local-file")
public class LocalFileController {
    @Value("${file.path}")
    private String path;

    private final LocalFileService localStorageService;

    public LocalFileController(LocalFileService localStorageService) {
        this.localStorageService = localStorageService;
    }

    @ApiOperation("查询文件")
    @GetMapping
    @PreAuthorize("@el.check('storage:list')")
    public ResponseEntity<Object> getLocalFiles(LocalFileQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(localStorageService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('storage:list')")
    public void download(HttpServletResponse response, LocalFileQueryCriteria criteria) throws IOException {
        localStorageService.download(localStorageService.queryAll(criteria), response);
    }

    @ApiOperation("上传文件")
    @PostMapping
    @PreAuthorize("@el.check('storage:add')")
    public ResponseEntity<Object> create(@RequestParam String name, @RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(localStorageService.create(name, file), HttpStatus.CREATED);
    }

    @ApiOperation("修改文件")
    @PutMapping
    @PreAuthorize("@el.check('storage:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody LocalFile resources){
        localStorageService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("多选删除")
    @DeleteMapping
    @ApiOperation("多选删除")
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        localStorageService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        LocalFileDto localFileDto = localStorageService.findByName(fileName);

        Resource resource = null;
        try {
            resource = FileUtil.loadFileAsResource(localFileDto.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new GlobalException(ex.getMessage());
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        String encodeFileName = null;
        try {
            encodeFileName = URLEncoder.encode(resource.getFile().getName(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodeFileName + "\"")
                .body(resource);
    }
}