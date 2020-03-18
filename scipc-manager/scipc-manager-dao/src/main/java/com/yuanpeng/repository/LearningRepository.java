package com.yuanpeng.repository;

import com.yuanpeng.entity.Learning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LearningRepository extends JpaRepository<Learning, Long>, JpaSpecificationExecutor<Learning> {
}
