package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.PermissionDto;
import com.yuanpeng.dto.RoleDto;
import com.yuanpeng.dto.UserSmallDto;
import com.yuanpeng.entity.Permission;
import com.yuanpeng.entity.Role;
import com.yuanpeng.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-17T14:25:23+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );
        role.setName( dto.getName() );
        role.setCode( dto.getCode() );
        role.setRemark( dto.getRemark() );
        role.setCreateTime( dto.getCreateTime() );
        role.setUsers( userSmallDtoSetToUserSet( dto.getUsers() ) );
        role.setPermissions( permissionDtoSetToPermissionSet( dto.getPermissions() ) );

        return role;
    }

    @Override
    public RoleDto toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( entity.getId() );
        roleDto.setName( entity.getName() );
        roleDto.setCode( entity.getCode() );
        roleDto.setRemark( entity.getRemark() );
        roleDto.setCreateTime( entity.getCreateTime() );
        roleDto.setPermissions( permissionSetToPermissionDtoSet( entity.getPermissions() ) );
        roleDto.setUsers( userSetToUserSmallDtoSet( entity.getUsers() ) );

        return roleDto;
    }

    @Override
    public List<Role> toEntity(List<RoleDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtoList.size() );
        for ( RoleDto roleDto : dtoList ) {
            list.add( toEntity( roleDto ) );
        }

        return list;
    }

    @Override
    public List<RoleDto> toDto(List<Role> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entityList.size() );
        for ( Role role : entityList ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    protected User userSmallDtoToUser(UserSmallDto userSmallDto) {
        if ( userSmallDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userSmallDto.getId() );
        user.setUsername( userSmallDto.getUsername() );

        return user;
    }

    protected Set<User> userSmallDtoSetToUserSet(Set<UserSmallDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<User> set1 = new HashSet<User>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserSmallDto userSmallDto : set ) {
            set1.add( userSmallDtoToUser( userSmallDto ) );
        }

        return set1;
    }

    protected Permission permissionDtoToPermission(PermissionDto permissionDto) {
        if ( permissionDto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setId( permissionDto.getId() );
        permission.setName( permissionDto.getName() );
        permission.setRemark( permissionDto.getRemark() );
        permission.setUrl( permissionDto.getUrl() );
        permission.setType( permissionDto.getType() );
        permission.setExpression( permissionDto.getExpression() );
        permission.setParentId( permissionDto.getParentId() );
        permission.setCreateTime( permissionDto.getCreateTime() );

        return permission;
    }

    protected Set<Permission> permissionDtoSetToPermissionSet(Set<PermissionDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Permission> set1 = new HashSet<Permission>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionDto permissionDto : set ) {
            set1.add( permissionDtoToPermission( permissionDto ) );
        }

        return set1;
    }

    protected PermissionDto permissionToPermissionDto(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( permission.getId() );
        permissionDto.setName( permission.getName() );
        permissionDto.setUrl( permission.getUrl() );
        permissionDto.setRemark( permission.getRemark() );
        permissionDto.setType( permission.getType() );
        permissionDto.setExpression( permission.getExpression() );
        permissionDto.setParentId( permission.getParentId() );
        permissionDto.setCreateTime( permission.getCreateTime() );

        return permissionDto;
    }

    protected Set<PermissionDto> permissionSetToPermissionDtoSet(Set<Permission> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionDto> set1 = new HashSet<PermissionDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Permission permission : set ) {
            set1.add( permissionToPermissionDto( permission ) );
        }

        return set1;
    }

    protected UserSmallDto userToUserSmallDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserSmallDto userSmallDto = new UserSmallDto();

        userSmallDto.setId( user.getId() );
        userSmallDto.setUsername( user.getUsername() );

        return userSmallDto;
    }

    protected Set<UserSmallDto> userSetToUserSmallDtoSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserSmallDto> set1 = new HashSet<UserSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToUserSmallDto( user ) );
        }

        return set1;
    }
}
