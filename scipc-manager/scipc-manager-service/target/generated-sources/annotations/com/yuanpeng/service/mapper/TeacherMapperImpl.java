package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.TeacherDto;
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
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public User toEntity(TeacherDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUsername( dto.getUsername() );
        user.setName( dto.getName() );
        user.setPhone( dto.getPhone() );
        user.setEmail( dto.getEmail() );
        user.setCollege( dto.getCollege() );
        user.setOffice( dto.getOffice() );
        user.setQq( dto.getQq() );

        return user;
    }

    @Override
    public TeacherDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setId( entity.getId() );
        teacherDto.setUsername( entity.getUsername() );
        teacherDto.setName( entity.getName() );
        teacherDto.setPhone( entity.getPhone() );
        teacherDto.setQq( entity.getQq() );
        teacherDto.setEmail( entity.getEmail() );
        teacherDto.setCollege( entity.getCollege() );
        teacherDto.setOffice( entity.getOffice() );

        return teacherDto;
    }

    @Override
    public List<User> toEntity(List<TeacherDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( TeacherDto teacherDto : dtoList ) {
            list.add( toEntity( teacherDto ) );
        }

        return list;
    }

    @Override
    public List<TeacherDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TeacherDto> list = new ArrayList<TeacherDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }
}
