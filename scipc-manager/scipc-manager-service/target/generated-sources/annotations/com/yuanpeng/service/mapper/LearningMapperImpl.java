package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.LearningDto;
import com.yuanpeng.entity.Learning;
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
public class LearningMapperImpl implements LearningMapper {

    @Override
    public Learning toEntity(LearningDto dto) {
        if ( dto == null ) {
            return null;
        }

        Learning learning = new Learning();

        learning.setId( dto.getId() );
        learning.setStudent( dto.getStudent() );
        learning.setDuration( dto.getDuration() );
        learning.setType( dto.getType() );
        learning.setCreateTime( dto.getCreateTime() );

        return learning;
    }

    @Override
    public LearningDto toDto(Learning entity) {
        if ( entity == null ) {
            return null;
        }

        LearningDto learningDto = new LearningDto();

        learningDto.setId( entity.getId() );
        learningDto.setStudent( entity.getStudent() );
        learningDto.setDuration( entity.getDuration() );
        learningDto.setType( entity.getType() );
        learningDto.setCreateTime( entity.getCreateTime() );

        return learningDto;
    }

    @Override
    public List<Learning> toEntity(List<LearningDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Learning> list = new ArrayList<Learning>( dtoList.size() );
        for ( LearningDto learningDto : dtoList ) {
            list.add( toEntity( learningDto ) );
        }

        return list;
    }

    @Override
    public List<LearningDto> toDto(List<Learning> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LearningDto> list = new ArrayList<LearningDto>( entityList.size() );
        for ( Learning learning : entityList ) {
            list.add( toDto( learning ) );
        }

        return list;
    }
}
