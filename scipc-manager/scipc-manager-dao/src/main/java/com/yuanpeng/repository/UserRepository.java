package com.yuanpeng.repository;

import com.yuanpeng.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @description: 用户
 * @author: YuanPeng
 * @create: 2020-02-14 17:12
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据角色查询
     * @param id
     * @return
     */
    @Query(value = "select * from sys_user u left join sys_user_role sur on u.id = sur.user_id where sur.role_id = ?1 ",
            countQuery = "select count(*) from sys_user;",
            nativeQuery = true)
    Page<User> findByRoles_Id(Long id, Pageable pageable);

    /**
     * 更新密码
     * @param username
     * @param newPassword
     * @param date
     */
    @Query(value = "update user set password = ?2, update_time = ?3 where username = ?1", nativeQuery = true)
    void updatePass(String username, String newPassword, Date date);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.violationCount = u.violationCount + 1 where u.id = ?1")
    void updateViolationCount(Long id);
}