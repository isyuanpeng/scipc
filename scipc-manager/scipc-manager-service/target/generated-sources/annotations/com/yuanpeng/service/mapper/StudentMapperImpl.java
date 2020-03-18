package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.StudentDto;
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
public class StudentMapperImpl implements StudentMapper {

    @Override
    public User toEntity(StudentDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUsername( dto.getUsername() );
        user.setName( dto.getName() );
        user.setPhone( dto.getPhone() );
        user.setEmail( dto.getEmail() );
        user.setMajor( dto.getMajor() );
        user.setCollege( dto.getCollege() );
        user.setGrade( dto.getGrade() );
        user.setComeYear( dto.getComeYear() );
        user.setOffice( dto.getOffice() );
        user.setQq( dto.getQq() );
        user.setCredit( dto.getCredit() );
        user.setWeekTime( dto.getWeekTime() );
        user.setLearningTime( dto.getLearningTime() );
        user.setViolationCount( dto.getViolationCount() );

        return user;
    }

    @Override
    public StudentDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setId( entity.getId() );
        studentDto.setUsername( entity.getUsername() );
        studentDto.setName( entity.getName() );
        studentDto.setPhone( entity.getPhone() );
        studentDto.setQq( entity.getQq() );
        studentDto.setEmail( entity.getEmail() );
        studentDto.setMajor( entity.getMajor() );
        studentDto.setCollege( entity.getCollege() );
        studentDto.setGrade( entity.getGrade() );
        studentDto.setComeYear( entity.getComeYear() );
        studentDto.setOffice( entity.getOffice() );
        studentDto.setCredit( entity.getCredit() );
        studentDto.setWeekTime( entity.getWeekTime() );
        studentDto.setLearningTime( entity.getLearningTime() );
        studentDto.setViolationCount( entity.getViolationCount() );

        return studentDto;
    }

    @Override
    public List<User> toEntity(List<StudentDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( StudentDto studentDto : dtoList ) {
            list.add( toEntity( studentDto ) );
        }

        return list;
    }

    @Override
    public List<StudentDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StudentDto> list = new ArrayList<StudentDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }
}
