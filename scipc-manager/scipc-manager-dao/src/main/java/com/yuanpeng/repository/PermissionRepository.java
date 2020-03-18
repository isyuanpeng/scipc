package com.yuanpeng.repository;

import com.yuanpeng.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-17 23:39
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
}
