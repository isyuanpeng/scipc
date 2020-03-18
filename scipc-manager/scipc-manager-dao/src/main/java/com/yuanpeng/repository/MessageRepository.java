package com.yuanpeng.repository;

import com.yuanpeng.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {
    @Query(value = "select * from sys_message where reciver_id=?1 order by id desc limit 1", nativeQuery = true)
    Message pull(Long id);
}
