package com.yuanpeng.repository;

import com.yuanpeng.entity.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-25 10:21
 */
public interface SignRepository extends JpaRepository<Sign, Long>, JpaSpecificationExecutor<Sign> {
}
