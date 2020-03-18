package com.yuanpeng;

import com.yuanpeng.dto.SignDto;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.Role;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.SignQueryCriteria;
import com.yuanpeng.repository.RoleRepository;
import com.yuanpeng.repository.UserRepository;
import com.yuanpeng.service.SignService;
import com.yuanpeng.service.UserService;
import net.bytebuddy.asm.Advice;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

/**
 * @description:
 * @author: YuanPeng
 * @create: 2020-02-16 15:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRun.class)
public class UserTest {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SignService signService;

    @Test
    @Ignore
    public void testUserCreate() {
        User user = new User();
        user.setUsername("511010");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setStatus(1);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(5L).get());
        user.setRoles(roles);

        UserDto userDto = userService.create(user);
        System.out.println(userDto);
    }

    @Test
    @Ignore
    public void testUserQueryAll() {
        List<UserDto> userDtos = userService.queryAll(null);
        System.out.println(userDtos);
    }

    @Test
    public void testScheduled() {
        UserDto user = userService.findById(21L);
        SignQueryCriteria signQueryCriteria = new SignQueryCriteria();
        signQueryCriteria.setStudentId(user.getId());
        String startTimeStr = LocalDate.now().toString() + " 06:00:00";
        String endTimeStr = LocalDate.now().plusDays(1).toString() + " 03:00:00";
        Timestamp startTime = Timestamp.valueOf(startTimeStr);
        Timestamp endTime = Timestamp.valueOf(endTimeStr);
        List<Timestamp> timestamps = new ArrayList<>();
        timestamps.add(startTime);
        timestamps.add(endTime);

        signQueryCriteria.setUpdateTime(timestamps);
        List<SignDto> signDtos = signService.queryAll(signQueryCriteria);
        System.out.println(timestamps);
        System.out.println(signDtos);
    }
}
