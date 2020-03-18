package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.RoleSmallDto;
import com.yuanpeng.entity.Role;
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
public class RoleSmallMapperImpl implements RoleSmallMapper {

    @Override
    public Role toEntity(RoleSmallDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );
        role.setName( dto.getName() );
        role.setCode( dto.getCode() );

        return role;
    }

    @Override
    public RoleSmallDto toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleSmallDto roleSmallDto = new RoleSmallDto();

        roleSmallDto.setId( entity.getId() );
        roleSmallDto.setName( entity.getName() );
        roleSmallDto.setCode( entity.getCode() );

        return roleSmallDto;
    }

    @Override
    public List<Role> toEntity(List<RoleSmallDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtoList.size() );
        for ( RoleSmallDto roleSmallDto : dtoList ) {
            list.add( toEntity( roleSmallDto ) );
        }

        return list;
    }

    @Override
    public List<RoleSmallDto> toDto(List<Role> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleSmallDto> list = new ArrayList<RoleSmallDto>( entityList.size() );
        for ( Role role : entityList ) {
            list.add( toDto( role ) );
        }

        return list;
    }
}
