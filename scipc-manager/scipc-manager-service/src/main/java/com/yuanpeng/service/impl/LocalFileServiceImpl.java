package com.yuanpeng.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.yuanpeng.common.exception.BadRequestException;
import com.yuanpeng.common.utils.*;
import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.LocalFile;
import com.yuanpeng.querycriteria.LocalFileQueryCriteria;
import com.yuanpeng.repository.LocalFileRepository;
import com.yuanpeng.service.LocalFileService;
import com.yuanpeng.service.mapper.LocalFileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-07 12:41
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LocalFileServiceImpl implements LocalFileService {

    private final LocalFileRepository localStorageRepository;

    private final LocalFileMapper localStorageMapper;

    @Value("${file.path}")
    private String path;

    @Value("${file.maxSize}")
    private long maxSize;

    public LocalFileServiceImpl(LocalFileRepository localStorageRepository, LocalFileMapper localStorageMapper) {
        this.localStorageRepository = localStorageRepository;
        this.localStorageMapper = localStorageMapper;
    }

    @Override
//    @Cacheable
    public Object queryAll(LocalFileQueryCriteria criteria, Pageable pageable){
        Page<LocalFile> page = localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(localStorageMapper::toDto));
    }

    @Override
//    @Cacheable
    public List<LocalFileDto> queryAll(LocalFileQueryCriteria criteria){
        return localStorageMapper.toDto(localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
//    @Cacheable(key = "#p0")
    public LocalFileDto findById(Long id){
        LocalFile localStorage = localStorageRepository.findById(id).orElseGet(LocalFile::new);
        ValidationUtil.isNull(localStorage.getId(),"LocalFile","id",id);
        return localStorageMapper.toDto(localStorage);
    }

    @Override
//    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public LocalFileDto create(String name, MultipartFile multipartFile) {
        FileUtil.checkSize(maxSize, multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        File file = FileUtil.upload(multipartFile, path + type +  File.separator, null);
        if(ObjectUtil.isNull(file)){
            throw new BadRequestException("上传失败");
        }
        try {
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            LocalFile localStorage = new LocalFile(
                    file.getName(),
                    suffix,
                    file.getPath(),
                    type,
                    FileUtil.getSize(multipartFile.getSize()),
                    SecurityUtils.getUsername()
            );
            return localStorageMapper.toDto(localStorageRepository.save(localStorage));
        }catch (Exception e){
            FileUtil.del(file);
            throw e;
        }
    }

    @Override
//    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(LocalFile resources) {
        LocalFile localStorage = localStorageRepository.findById(resources.getId()).orElseGet(LocalFile::new);
        ValidationUtil.isNull( localStorage.getId(),"LocalFile","id",resources.getId());
        localStorage.copy(resources);
        localStorageRepository.save(localStorage);
    }

    @Override
//    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            LocalFile storage = localStorageRepository.findById(id).orElseGet(LocalFile::new);
            FileUtil.del(storage.getPath());
            localStorageRepository.delete(storage);
        }
    }

    @Override
    public void download(List<LocalFileDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LocalFileDto localStorageDTO : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("文件名", localStorageDTO.getName());
            map.put("文件类型", localStorageDTO.getType());
            map.put("文件大小", localStorageDTO.getSize());
            map.put("操作人", localStorageDTO.getOperate());
            map.put("创建日期", localStorageDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public LocalFileDto findByName(String name) {
        return localStorageMapper.toDto(localStorageRepository.findByName(name));
    }
}

