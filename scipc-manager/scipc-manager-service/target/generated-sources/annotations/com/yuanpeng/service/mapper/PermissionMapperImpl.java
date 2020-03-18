package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.PermissionDto;
import com.yuanpeng.entity.Permission;
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
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public Permission toEntity(PermissionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setId( dto.getId() );
        permission.setName( dto.getName() );
        permission.setRemark( dto.getRemark() );
        permission.setUrl( dto.getUrl() );
        permission.setType( dto.getType() );
        permission.setExpression( dto.getExpression() );
        permission.setParentId( dto.getParentId() );
        permission.setCreateTime( dto.getCreateTime() );

        return permission;
    }

    @Override
    public PermissionDto toDto(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( entity.getId() );
        permissionDto.setName( entity.getName() );
        permissionDto.setUrl( entity.getUrl() );
        permissionDto.setRemark( entity.getRemark() );
        permissionDto.setType( entity.getType() );
        permissionDto.setExpression( entity.getExpression() );
        permissionDto.setParentId( entity.getParentId() );
        permissionDto.setCreateTime( entity.getCreateTime() );

        return permissionDto;
    }

    @Override
    public List<Permission> toEntity(List<PermissionDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Permission> list = new ArrayList<Permission>( dtoList.size() );
        for ( PermissionDto permissionDto : dtoList ) {
            list.add( toEntity( permissionDto ) );
        }

        return list;
    }

    @Override
    public List<PermissionDto> toDto(List<Permission> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PermissionDto> list = new ArrayList<PermissionDto>( entityList.size() );
        for ( Permission permission : entityList ) {
            list.add( toDto( permission ) );
        }

        return list;
    }
}
