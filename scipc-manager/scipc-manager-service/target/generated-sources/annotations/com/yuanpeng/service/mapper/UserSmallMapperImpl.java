package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.UserSmallDto;
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
public class UserSmallMapperImpl implements UserSmallMapper {

    @Override
    public User toEntity(UserSmallDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUsername( dto.getUsername() );

        return user;
    }

    @Override
    public UserSmallDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserSmallDto userSmallDto = new UserSmallDto();

        userSmallDto.setId( entity.getId() );
        userSmallDto.setUsername( entity.getUsername() );

        return userSmallDto;
    }

    @Override
    public List<User> toEntity(List<UserSmallDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserSmallDto userSmallDto : dtoList ) {
            list.add( toEntity( userSmallDto ) );
        }

        return list;
    }

    @Override
    public List<UserSmallDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserSmallDto> list = new ArrayList<UserSmallDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }
}
