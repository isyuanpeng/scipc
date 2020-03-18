package com.yuanpeng.repository;

import com.yuanpeng.entity.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ViolationRepository extends JpaRepository<Violation, Long>, JpaSpecificationExecutor<Violation> {
}
