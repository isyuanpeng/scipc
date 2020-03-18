package com.yuanpeng.repository;

import com.yuanpeng.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-15 16:53
 */
public interface LeaveRepository extends JpaRepository<Leave, Long>, JpaSpecificationExecutor<Leave> {
}
