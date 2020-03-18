package com.yuanpeng.repository;

import com.yuanpeng.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.Set;

/**
 * @description: 角色Dao
 * @author: YuanPeng
 * @create: 2020-02-15 19:09
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 根据用户Id 查询Role列表
     * @param id
     * @return
     */
    Set<Role> findByUsers_Id(Long id);

    Optional<Role> findByCode(String code);
}