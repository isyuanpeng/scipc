package com.yuanpeng.controller;

import com.yuanpeng.aop.Log;
import com.yuanpeng.common.utils.FileUtil;
import com.yuanpeng.dto.HomeworkDto;
import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.Homework;
import com.yuanpeng.querycriteria.HomeworkQueryCriteria;
import com.yuanpeng.service.HomeworkService;
import com.yuanpeng.service.LocalFileService;
import com.yuanpeng.service.mapper.LocalFileMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 09:34
 */
@Api(tags = "作业管理")
@RestController
@RequestMapping("/api/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('homework:list')")
    public void download(HttpServletResponse response, HomeworkQueryCriteria criteria) throws IOException {
        homeworkService.download(homeworkService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询作业")
    @ApiOperation("查询作业")
    @PreAuthorize("@el.check('homework:list')")
    public ResponseEntity<Object> getHomeworks(HomeworkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(homeworkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增作业")
    @ApiOperation("新增作业")
    @PreAuthorize("@el.check('homework:add')")
    public ResponseEntity<Object> create(String name, String remark, Long studentId, Long courseId, String courseChapter, @RequestParam("file") MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.replace("\"", ""));
        map.put("remark", remark.replace("\"", ""));
        map.put("studentId", studentId);
        map.put("courseId", courseId);
        map.put("courseChapter", courseChapter.replace("\"", ""));

        HomeworkDto homeworkDto = homeworkService.create(map, file);
        return new ResponseEntity<>(homeworkDto, HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改作业")
    @ApiOperation("修改作业")
    @PreAuthorize("@el.check('homework:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Homework resources){
        homeworkService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/correct")
    @Log("批改作业")
    @ApiOperation("批改作业")
    @PreAuthorize("@el.check('homework:edit')")
    public ResponseEntity<Object> correct(Long homeworkId, String score, String response, MultipartFile file){
        homeworkService.correct(homeworkId, score.replace("\"", ""), response.replace("\"", ""), file);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/allot")
    @Log("分配作业")
    @ApiOperation("分配作业")
    @PreAuthorize("@el.check('homework:edit')")
    public ResponseEntity<Object> allot(Long homeworkId, Long userId){
        homeworkService.allot(homeworkId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除作业")
    @ApiOperation("删除作业")
    @PreAuthorize("@el.check('homework:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        homeworkService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}