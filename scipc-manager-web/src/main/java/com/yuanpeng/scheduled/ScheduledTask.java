package com.yuanpeng.scheduled;

import com.yuanpeng.dto.SignDto;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.dto.UserSmallDto;
import com.yuanpeng.entity.Learning;
import com.yuanpeng.entity.User;
import com.yuanpeng.querycriteria.SignQueryCriteria;
import com.yuanpeng.service.LearningService;
import com.yuanpeng.service.RoleService;
import com.yuanpeng.service.SignService;
import com.yuanpeng.service.UserService;
import com.yuanpeng.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class ScheduledTask {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private final SignService signService;

    private final UserService userService;

    private final RoleService roleService;

    private final LearningService learningService;

    private final UserMapper userMapper;

    public ScheduledTask(SignService signService, UserService userService, RoleService roleService, LearningService learningService, UserMapper userMapper) {
        this.signService = signService;
        this.userService = userService;
        this.roleService = roleService;
        this.learningService = learningService;
        this.userMapper = userMapper;
    }

    /**
     * 生成每天的学习记录
     */
    @Scheduled(cron = "0 01 04 * * ?")
    private void generateDayLearning() {
        Set<UserSmallDto> userSmallDtos = roleService.findById(4L).getUsers();
        for (UserSmallDto userSmallDto:
             userSmallDtos) {
            UserDto user = userService.findById(userSmallDto.getId());
            SignQueryCriteria signQueryCriteria = new SignQueryCriteria();
            signQueryCriteria.setStudentId(user.getId());
            String startTimeStr = LocalDate.now().toString() + " 06:00:00";
            String endTimeStr = LocalDate.now().plusDays(1).toString() + " 04:00:00";
            Timestamp startTime = Timestamp.valueOf(startTimeStr);
            Timestamp endTime = Timestamp.valueOf(endTimeStr);
            List<Timestamp> timestamps = new ArrayList<>();
            timestamps.add(startTime);
            timestamps.add(endTime);

            signQueryCriteria.setUpdateTime(timestamps);
            List<SignDto> signDtos = signService.queryAll(signQueryCriteria);
            if (signDtos.size() == 0) {
                continue;
            }
            long total = 0;
            for (SignDto signDto:
                    signDtos) {
                total += signDto.getDifference();
            }
            Learning learning = new Learning();
            learning.setDuration(total);
            learning.setStudent(userMapper.toEntity(user));
            learning.setCreateTime(new Timestamp(new Date().getTime()));
            learning.setType(1);
            learningService.create(learning);

        }
    }

    /**
     * 生成周学习记录
     */
    @Scheduled(cron = "0 0 0 ? * 7")
    private void generateWeekLearning() {
        Set<UserSmallDto> userSmallDtos = roleService.findById(4L).getUsers();
        for (UserSmallDto userSmallDto:
                userSmallDtos) {
            UserDto user = userService.findById(userSmallDto.getId());
            Learning learning = new Learning();
            learning.setDuration(user.getWeekTime());
            learning.setStudent(userMapper.toEntity(user));
            learning.setCreateTime(new Timestamp(new Date().getTime()));
            learning.setType(2);
            learningService.create(learning);

            User weekUser = new User();
            weekUser.setWeekTime(0L);
            User updateUser = userMapper.toEntity(user);
            updateUser.copy(weekUser);
            userService.update(updateUser);
        }
    }

}
