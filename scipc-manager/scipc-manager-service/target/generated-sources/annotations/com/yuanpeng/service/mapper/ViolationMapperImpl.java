package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.ViolationDto;
import com.yuanpeng.entity.Violation;
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
public class ViolationMapperImpl implements ViolationMapper {

    @Override
    public Violation toEntity(ViolationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Violation violation = new Violation();

        violation.setId( dto.getId() );
        violation.setStudent( dto.getStudent() );
        violation.setTeacher( dto.getTeacher() );
        violation.setReason( dto.getReason() );
        violation.setCreateTime( dto.getCreateTime() );
        violation.setUpdateTime( dto.getUpdateTime() );

        return violation;
    }

    @Override
    public ViolationDto toDto(Violation entity) {
        if ( entity == null ) {
            return null;
        }

        ViolationDto violationDto = new ViolationDto();

        violationDto.setId( entity.getId() );
        violationDto.setStudent( entity.getStudent() );
        violationDto.setTeacher( entity.getTeacher() );
        violationDto.setReason( entity.getReason() );
        violationDto.setCreateTime( entity.getCreateTime() );
        violationDto.setUpdateTime( entity.getUpdateTime() );

        return violationDto;
    }

    @Override
    public List<Violation> toEntity(List<ViolationDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Violation> list = new ArrayList<Violation>( dtoList.size() );
        for ( ViolationDto violationDto : dtoList ) {
            list.add( toEntity( violationDto ) );
        }

        return list;
    }

    @Override
    public List<ViolationDto> toDto(List<Violation> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ViolationDto> list = new ArrayList<ViolationDto>( entityList.size() );
        for ( Violation violation : entityList ) {
            list.add( toDto( violation ) );
        }

        return list;
    }
}
