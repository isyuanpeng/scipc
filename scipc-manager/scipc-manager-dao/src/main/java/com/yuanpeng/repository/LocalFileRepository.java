package com.yuanpeng.repository;

import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.LocalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-03-07 12:38
 */
public interface LocalFileRepository extends JpaRepository<LocalFile, Long>, JpaSpecificationExecutor<LocalFile> {
    LocalFile findByName(String name);
}