package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.CourseDto;
import com.yuanpeng.dto.LeaveDto;
import com.yuanpeng.dto.RoleSmallDto;
import com.yuanpeng.dto.StudentCourseDto;
import com.yuanpeng.dto.StudentDto;
import com.yuanpeng.dto.TeacherDto;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.entity.Leave;
import com.yuanpeng.entity.Role;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-17T14:25:24+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class LeaveMapperImpl implements LeaveMapper {

    @Override
    public Leave toEntity(LeaveDto dto) {
        if ( dto == null ) {
            return null;
        }

        Leave leave = new Leave();

        leave.setId( dto.getId() );
        leave.setDatetime( dto.getDatetime() );
        leave.setReason( dto.getReason() );
        leave.setStudent( userDtoToUser( dto.getStudent() ) );
        leave.setIsRead( dto.getIsRead() );
        leave.setIsApprove( dto.getIsApprove() );
        leave.setTeacher( userDtoToUser( dto.getTeacher() ) );
        leave.setResContent( dto.getResContent() );
        leave.setCreateTime( dto.getCreateTime() );

        return leave;
    }

    @Override
    public LeaveDto toDto(Leave entity) {
        if ( entity == null ) {
            return null;
        }

        LeaveDto leaveDto = new LeaveDto();

        leaveDto.setId( entity.getId() );
        leaveDto.setDatetime( entity.getDatetime() );
        leaveDto.setReason( entity.getReason() );
        leaveDto.setStudent( userToUserDto( entity.getStudent() ) );
        leaveDto.setIsRead( entity.getIsRead() );
        leaveDto.setIsApprove( entity.getIsApprove() );
        leaveDto.setTeacher( userToUserDto( entity.getTeacher() ) );
        leaveDto.setResContent( entity.getResContent() );
        leaveDto.setCreateTime( entity.getCreateTime() );

        return leaveDto;
    }

    @Override
    public List<Leave> toEntity(List<LeaveDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Leave> list = new ArrayList<Leave>( dtoList.size() );
        for ( LeaveDto leaveDto : dtoList ) {
            list.add( toEntity( leaveDto ) );
        }

        return list;
    }

    @Override
    public List<LeaveDto> toDto(List<Leave> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LeaveDto> list = new ArrayList<LeaveDto>( entityList.size() );
        for ( Leave leave : entityList ) {
            list.add( toDto( leave ) );
        }

        return list;
    }

    protected Role roleSmallDtoToRole(RoleSmallDto roleSmallDto) {
        if ( roleSmallDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleSmallDto.getId() );
        role.setName( roleSmallDto.getName() );
        role.setCode( roleSmallDto.getCode() );

        return role;
    }

    protected Set<Role> roleSmallDtoSetToRoleSet(Set<RoleSmallDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleSmallDto roleSmallDto : set ) {
            set1.add( roleSmallDtoToRole( roleSmallDto ) );
        }

        return set1;
    }

    protected Course courseDtoToCourse(CourseDto courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( courseDto.getId() );
        course.setCode( courseDto.getCode() );
        course.setName( courseDto.getName() );
        course.setChapterCount( courseDto.getChapterCount() );
        course.setUrl( courseDto.getUrl() );
        course.setCredit( courseDto.getCredit() );
        course.setIsRequired( courseDto.getIsRequired() );
        course.setRemark( courseDto.getRemark() );
        course.setCourseType( courseDto.getCourseType() );
        course.setStatus( courseDto.getStatus() );
        course.setCreateTime( courseDto.getCreateTime() );
        course.setUpdateTime( courseDto.getUpdateTime() );

        return course;
    }

    protected User studentDtoToUser(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( studentDto.getId() );
        user.setUsername( studentDto.getUsername() );
        user.setName( studentDto.getName() );
        user.setPhone( studentDto.getPhone() );
        user.setEmail( studentDto.getEmail() );
        user.setMajor( studentDto.getMajor() );
        user.setCollege( studentDto.getCollege() );
        user.setGrade( studentDto.getGrade() );
        user.setComeYear( studentDto.getComeYear() );
        user.setOffice( studentDto.getOffice() );
        user.setQq( studentDto.getQq() );
        user.setCredit( studentDto.getCredit() );
        user.setWeekTime( studentDto.getWeekTime() );
        user.setLearningTime( studentDto.getLearningTime() );
        user.setViolationCount( studentDto.getViolationCount() );

        return user;
    }

    protected User teacherDtoToUser(TeacherDto teacherDto) {
        if ( teacherDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( teacherDto.getId() );
        user.setUsername( teacherDto.getUsername() );
        user.setName( teacherDto.getName() );
        user.setPhone( teacherDto.getPhone() );
        user.setEmail( teacherDto.getEmail() );
        user.setCollege( teacherDto.getCollege() );
        user.setOffice( teacherDto.getOffice() );
        user.setQq( teacherDto.getQq() );

        return user;
    }

    protected StudentCourse studentCourseDtoToStudentCourse(StudentCourseDto studentCourseDto) {
        if ( studentCourseDto == null ) {
            return null;
        }

        StudentCourse studentCourse = new StudentCourse();

        studentCourse.setId( studentCourseDto.getId() );
        studentCourse.setCourse( courseDtoToCourse( studentCourseDto.getCourse() ) );
        studentCourse.setStudent( studentDtoToUser( studentCourseDto.getStudent() ) );
        studentCourse.setTeacher( teacherDtoToUser( studentCourseDto.getTeacher() ) );
        studentCourse.setScore( studentCourseDto.getScore() );
        studentCourse.setIsAchieve( studentCourseDto.getIsAchieve() );
        studentCourse.setProgress( studentCourseDto.getProgress() );
        studentCourse.setCreateTime( studentCourseDto.getCreateTime() );
        studentCourse.setUpdateTime( studentCourseDto.getUpdateTime() );

        return studentCourse;
    }

    protected Set<StudentCourse> studentCourseDtoSetToStudentCourseSet(Set<StudentCourseDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<StudentCourse> set1 = new HashSet<StudentCourse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StudentCourseDto studentCourseDto : set ) {
            set1.add( studentCourseDtoToStudentCourse( studentCourseDto ) );
        }

        return set1;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );
        user.setName( userDto.getName() );
        user.setPhone( userDto.getPhone() );
        user.setEmail( userDto.getEmail() );
        user.setMajor( userDto.getMajor() );
        user.setCollege( userDto.getCollege() );
        user.setGrade( userDto.getGrade() );
        user.setComeYear( userDto.getComeYear() );
        user.setQq( userDto.getQq() );
        user.setCredit( userDto.getCredit() );
        user.setWeekTime( userDto.getWeekTime() );
        user.setLearningTime( userDto.getLearningTime() );
        user.setViolationCount( userDto.getViolationCount() );
        user.setStatus( userDto.getStatus() );
        user.setCreateTime( userDto.getCreateTime() );
        user.setRoles( roleSmallDtoSetToRoleSet( userDto.getRoles() ) );
        user.setStudentCourses( studentCourseDtoSetToStudentCourseSet( userDto.getStudentCourses() ) );

        return user;
    }

    protected RoleSmallDto roleToRoleSmallDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleSmallDto roleSmallDto = new RoleSmallDto();

        roleSmallDto.setId( role.getId() );
        roleSmallDto.setName( role.getName() );
        roleSmallDto.setCode( role.getCode() );

        return roleSmallDto;
    }

    protected Set<RoleSmallDto> roleSetToRoleSmallDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleSmallDto> set1 = new HashSet<RoleSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleSmallDto( role ) );
        }

        return set1;
    }

    protected CourseDto courseToCourseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setId( course.getId() );
        courseDto.setCode( course.getCode() );
        courseDto.setName( course.getName() );
        courseDto.setChapterCount( course.getChapterCount() );
        courseDto.setUrl( course.getUrl() );
        courseDto.setCredit( course.getCredit() );
        courseDto.setIsRequired( course.getIsRequired() );
        courseDto.setRemark( course.getRemark() );
        courseDto.setCourseType( course.getCourseType() );
        courseDto.setStatus( course.getStatus() );
        courseDto.setCreateTime( course.getCreateTime() );
        courseDto.setUpdateTime( course.getUpdateTime() );

        return courseDto;
    }

    protected StudentDto userToStudentDto(User user) {
        if ( user == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setId( user.getId() );
        studentDto.setUsername( user.getUsername() );
        studentDto.setName( user.getName() );
        studentDto.setPhone( user.getPhone() );
        studentDto.setQq( user.getQq() );
        studentDto.setEmail( user.getEmail() );
        studentDto.setMajor( user.getMajor() );
        studentDto.setCollege( user.getCollege() );
        studentDto.setGrade( user.getGrade() );
        studentDto.setComeYear( user.getComeYear() );
        studentDto.setOffice( user.getOffice() );
        studentDto.setCredit( user.getCredit() );
        studentDto.setWeekTime( user.getWeekTime() );
        studentDto.setLearningTime( user.getLearningTime() );
        studentDto.setViolationCount( user.getViolationCount() );

        return studentDto;
    }

    protected TeacherDto userToTeacherDto(User user) {
        if ( user == null ) {
            return null;
        }

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setId( user.getId() );
        teacherDto.setUsername( user.getUsername() );
        teacherDto.setName( user.getName() );
        teacherDto.setPhone( user.getPhone() );
        teacherDto.setQq( user.getQq() );
        teacherDto.setEmail( user.getEmail() );
        teacherDto.setCollege( user.getCollege() );
        teacherDto.setOffice( user.getOffice() );

        return teacherDto;
    }

    protected StudentCourseDto studentCourseToStudentCourseDto(StudentCourse studentCourse) {
        if ( studentCourse == null ) {
            return null;
        }

        StudentCourseDto studentCourseDto = new StudentCourseDto();

        studentCourseDto.setId( studentCourse.getId() );
        studentCourseDto.setCourse( courseToCourseDto( studentCourse.getCourse() ) );
        studentCourseDto.setStudent( userToStudentDto( studentCourse.getStudent() ) );
        studentCourseDto.setTeacher( userToTeacherDto( studentCourse.getTeacher() ) );
        studentCourseDto.setScore( studentCourse.getScore() );
        studentCourseDto.setIsAchieve( studentCourse.getIsAchieve() );
        studentCourseDto.setProgress( studentCourse.getProgress() );
        studentCourseDto.setCreateTime( studentCourse.getCreateTime() );
        studentCourseDto.setUpdateTime( studentCourse.getUpdateTime() );

        return studentCourseDto;
    }

    protected Set<StudentCourseDto> studentCourseSetToStudentCourseDtoSet(Set<StudentCourse> set) {
        if ( set == null ) {
            return null;
        }

        Set<StudentCourseDto> set1 = new HashSet<StudentCourseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StudentCourse studentCourse : set ) {
            set1.add( studentCourseToStudentCourseDto( studentCourse ) );
        }

        return set1;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setName( user.getName() );
        userDto.setRoles( roleSetToRoleSmallDtoSet( user.getRoles() ) );
        userDto.setStudentCourses( studentCourseSetToStudentCourseDtoSet( user.getStudentCourses() ) );
        userDto.setPhone( user.getPhone() );
        userDto.setQq( user.getQq() );
        userDto.setEmail( user.getEmail() );
        userDto.setMajor( user.getMajor() );
        userDto.setCollege( user.getCollege() );
        userDto.setGrade( user.getGrade() );
        userDto.setComeYear( user.getComeYear() );
        userDto.setStatus( user.getStatus() );
        userDto.setCreateTime( user.getCreateTime() );
        userDto.setCredit( user.getCredit() );
        userDto.setWeekTime( user.getWeekTime() );
        userDto.setLearningTime( user.getLearningTime() );
        userDto.setViolationCount( user.getViolationCount() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
