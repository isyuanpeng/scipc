package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.CourseDto;
import com.yuanpeng.dto.HomeworkDto;
import com.yuanpeng.dto.HomeworkScoreDto;
import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.dto.StudentDto;
import com.yuanpeng.dto.TeacherDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.entity.Homework;
import com.yuanpeng.entity.HomeworkScore;
import com.yuanpeng.entity.LocalFile;
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
public class HomeworkMapperImpl implements HomeworkMapper {

    @Override
    public Homework toEntity(HomeworkDto dto) {
        if ( dto == null ) {
            return null;
        }

        Homework homework = new Homework();

        homework.setId( dto.getId() );
        homework.setName( dto.getName() );
        homework.setCourseChapter( dto.getCourseChapter() );
        homework.setCourse( courseDtoToCourse( dto.getCourse() ) );
        homework.setStudent( studentDtoToUser( dto.getStudent() ) );
        homework.setRemark( dto.getRemark() );
        homework.setFile( localFileDtoToLocalFile( dto.getFile() ) );
        homework.setIsRead( dto.getIsRead() );
        homework.setScore( dto.getScore() );
        homework.setTeacher( dto.getTeacher() );
        homework.setResponseContent( dto.getResponseContent() );
        homework.setResponseFile( localFileDtoToLocalFile( dto.getResponseFile() ) );
        homework.setAlloter( dto.getAlloter() );
        homework.setCreateTime( dto.getCreateTime() );

        return homework;
    }

    @Override
    public HomeworkDto toDto(Homework entity) {
        if ( entity == null ) {
            return null;
        }

        HomeworkDto homeworkDto = new HomeworkDto();

        homeworkDto.setId( entity.getId() );
        homeworkDto.setName( entity.getName() );
        homeworkDto.setCourse( courseToCourseDto( entity.getCourse() ) );
        homeworkDto.setCourseChapter( entity.getCourseChapter() );
        homeworkDto.setStudent( userToStudentDto( entity.getStudent() ) );
        homeworkDto.setRemark( entity.getRemark() );
        homeworkDto.setIsRead( entity.getIsRead() );
        homeworkDto.setFile( localFileToLocalFileDto( entity.getFile() ) );
        homeworkDto.setScore( entity.getScore() );
        homeworkDto.setTeacher( entity.getTeacher() );
        homeworkDto.setResponseContent( entity.getResponseContent() );
        homeworkDto.setResponseFile( localFileToLocalFileDto( entity.getResponseFile() ) );
        homeworkDto.setAlloter( entity.getAlloter() );
        homeworkDto.setCreateTime( entity.getCreateTime() );

        return homeworkDto;
    }

    @Override
    public List<Homework> toEntity(List<HomeworkDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Homework> list = new ArrayList<Homework>( dtoList.size() );
        for ( HomeworkDto homeworkDto : dtoList ) {
            list.add( toEntity( homeworkDto ) );
        }

        return list;
    }

    @Override
    public List<HomeworkDto> toDto(List<Homework> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HomeworkDto> list = new ArrayList<HomeworkDto>( entityList.size() );
        for ( Homework homework : entityList ) {
            list.add( toDto( homework ) );
        }

        return list;
    }

    @Override
    public HomeworkScoreDto homeworkScoreToHomeworkScoreDto(HomeworkScore homeworkScore) {
        if ( homeworkScore == null ) {
            return null;
        }

        HomeworkScoreDto homeworkScoreDto = new HomeworkScoreDto();

        homeworkScoreDto.setId( homeworkScore.getId() );
        homeworkScoreDto.setTeacher( userToTeacherDto( homeworkScore.getTeacher() ) );
        homeworkScoreDto.setScore( homeworkScore.getScore() );
        homeworkScoreDto.setRemark( homeworkScore.getRemark() );

        return homeworkScoreDto;
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

    protected LocalFile localFileDtoToLocalFile(LocalFileDto localFileDto) {
        if ( localFileDto == null ) {
            return null;
        }

        LocalFile localFile = new LocalFile();

        localFile.setId( localFileDto.getId() );
        localFile.setName( localFileDto.getName() );
        localFile.setSuffix( localFileDto.getSuffix() );
        localFile.setPath( localFileDto.getPath() );
        localFile.setType( localFileDto.getType() );
        localFile.setSize( localFileDto.getSize() );
        localFile.setOperate( localFileDto.getOperate() );
        localFile.setCreateTime( localFileDto.getCreateTime() );

        return localFile;
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

    protected LocalFileDto localFileToLocalFileDto(LocalFile localFile) {
        if ( localFile == null ) {
            return null;
        }

        LocalFileDto localFileDto = new LocalFileDto();

        localFileDto.setId( localFile.getId() );
        localFileDto.setName( localFile.getName() );
        localFileDto.setPath( localFile.getPath() );
        localFileDto.setSuffix( localFile.getSuffix() );
        localFileDto.setType( localFile.getType() );
        localFileDto.setSize( localFile.getSize() );
        localFileDto.setOperate( localFile.getOperate() );
        localFileDto.setCreateTime( localFile.getCreateTime() );

        return localFileDto;
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
