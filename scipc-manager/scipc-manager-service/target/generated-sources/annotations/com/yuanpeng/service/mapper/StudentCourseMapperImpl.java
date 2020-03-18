package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.CourseDto;
import com.yuanpeng.dto.StudentCourseDto;
import com.yuanpeng.dto.StudentDto;
import com.yuanpeng.dto.TeacherDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.entity.StudentCourse;
import com.yuanpeng.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-17T14:25:23+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class StudentCourseMapperImpl implements StudentCourseMapper {

    @Override
    public StudentCourse toEntity(StudentCourseDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentCourse studentCourse = new StudentCourse();

        studentCourse.setId( dto.getId() );
        studentCourse.setCourse( courseDtoToCourse( dto.getCourse() ) );
        studentCourse.setStudent( studentDtoToUser( dto.getStudent() ) );
        studentCourse.setTeacher( teacherDtoToUser( dto.getTeacher() ) );
        studentCourse.setScore( dto.getScore() );
        studentCourse.setIsAchieve( dto.getIsAchieve() );
        studentCourse.setProgress( dto.getProgress() );
        studentCourse.setCreateTime( dto.getCreateTime() );
        studentCourse.setUpdateTime( dto.getUpdateTime() );

        return studentCourse;
    }

    @Override
    public StudentCourseDto toDto(StudentCourse entity) {
        if ( entity == null ) {
            return null;
        }

        StudentCourseDto studentCourseDto = new StudentCourseDto();

        studentCourseDto.setId( entity.getId() );
        studentCourseDto.setCourse( courseToCourseDto( entity.getCourse() ) );
        studentCourseDto.setStudent( userToStudentDto( entity.getStudent() ) );
        studentCourseDto.setTeacher( userToTeacherDto( entity.getTeacher() ) );
        studentCourseDto.setScore( entity.getScore() );
        studentCourseDto.setIsAchieve( entity.getIsAchieve() );
        studentCourseDto.setProgress( entity.getProgress() );
        studentCourseDto.setCreateTime( entity.getCreateTime() );
        studentCourseDto.setUpdateTime( entity.getUpdateTime() );

        return studentCourseDto;
    }

    @Override
    public List<StudentCourse> toEntity(List<StudentCourseDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<StudentCourse> list = new ArrayList<StudentCourse>( dtoList.size() );
        for ( StudentCourseDto studentCourseDto : dtoList ) {
            list.add( toEntity( studentCourseDto ) );
        }

        return list;
    }

    @Override
    public List<StudentCourseDto> toDto(List<StudentCourse> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StudentCourseDto> list = new ArrayList<StudentCourseDto>( entityList.size() );
        for ( StudentCourse studentCourse : entityList ) {
            list.add( toDto( studentCourse ) );
        }

        return list;
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
}
