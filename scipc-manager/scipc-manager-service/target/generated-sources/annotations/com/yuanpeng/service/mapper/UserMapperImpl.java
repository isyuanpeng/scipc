package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.CourseDto;
import com.yuanpeng.dto.RoleSmallDto;
import com.yuanpeng.dto.StudentCourseDto;
import com.yuanpeng.dto.StudentDto;
import com.yuanpeng.dto.TeacherDto;
import com.yuanpeng.dto.UserDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.entity.Role;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-17T14:25:23+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleSmallMapper roleSmallMapper;

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUsername( dto.getUsername() );
        user.setPassword( dto.getPassword() );
        user.setName( dto.getName() );
        user.setPhone( dto.getPhone() );
        user.setEmail( dto.getEmail() );
        user.setMajor( dto.getMajor() );
        user.setCollege( dto.getCollege() );
        user.setGrade( dto.getGrade() );
        user.setComeYear( dto.getComeYear() );
        user.setQq( dto.getQq() );
        user.setCredit( dto.getCredit() );
        user.setWeekTime( dto.getWeekTime() );
        user.setLearningTime( dto.getLearningTime() );
        user.setViolationCount( dto.getViolationCount() );
        user.setStatus( dto.getStatus() );
        user.setCreateTime( dto.getCreateTime() );
        user.setRoles( roleSmallDtoSetToRoleSet( dto.getRoles() ) );
        user.setStudentCourses( studentCourseDtoSetToStudentCourseSet( dto.getStudentCourses() ) );

        return user;
    }

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( entity.getId() );
        userDto.setUsername( entity.getUsername() );
        userDto.setName( entity.getName() );
        userDto.setRoles( roleSetToRoleSmallDtoSet( entity.getRoles() ) );
        userDto.setStudentCourses( studentCourseSetToStudentCourseDtoSet( entity.getStudentCourses() ) );
        userDto.setPhone( entity.getPhone() );
        userDto.setQq( entity.getQq() );
        userDto.setEmail( entity.getEmail() );
        userDto.setMajor( entity.getMajor() );
        userDto.setCollege( entity.getCollege() );
        userDto.setGrade( entity.getGrade() );
        userDto.setComeYear( entity.getComeYear() );
        userDto.setStatus( entity.getStatus() );
        userDto.setCreateTime( entity.getCreateTime() );
        userDto.setCredit( entity.getCredit() );
        userDto.setWeekTime( entity.getWeekTime() );
        userDto.setLearningTime( entity.getLearningTime() );
        userDto.setViolationCount( entity.getViolationCount() );
        userDto.setPassword( entity.getPassword() );

        return userDto;
    }

    @Override
    public List<User> toEntity(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    protected Set<Role> roleSmallDtoSetToRoleSet(Set<RoleSmallDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleSmallDto roleSmallDto : set ) {
            set1.add( roleSmallMapper.toEntity( roleSmallDto ) );
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

    protected Set<RoleSmallDto> roleSetToRoleSmallDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleSmallDto> set1 = new HashSet<RoleSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleSmallMapper.toDto( role ) );
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
}
