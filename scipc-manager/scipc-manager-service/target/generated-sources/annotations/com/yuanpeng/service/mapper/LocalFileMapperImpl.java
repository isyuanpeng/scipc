package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.LocalFileDto;
import com.yuanpeng.entity.LocalFile;
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
public class LocalFileMapperImpl implements LocalFileMapper {

    @Override
    public LocalFile toEntity(LocalFileDto dto) {
        if ( dto == null ) {
            return null;
        }

        LocalFile localFile = new LocalFile();

        localFile.setId( dto.getId() );
        localFile.setName( dto.getName() );
        localFile.setSuffix( dto.getSuffix() );
        localFile.setPath( dto.getPath() );
        localFile.setType( dto.getType() );
        localFile.setSize( dto.getSize() );
        localFile.setOperate( dto.getOperate() );
        localFile.setCreateTime( dto.getCreateTime() );

        return localFile;
    }

    @Override
    public LocalFileDto toDto(LocalFile entity) {
        if ( entity == null ) {
            return null;
        }

        LocalFileDto localFileDto = new LocalFileDto();

        localFileDto.setId( entity.getId() );
        localFileDto.setName( entity.getName() );
        localFileDto.setPath( entity.getPath() );
        localFileDto.setSuffix( entity.getSuffix() );
        localFileDto.setType( entity.getType() );
        localFileDto.setSize( entity.getSize() );
        localFileDto.setOperate( entity.getOperate() );
        localFileDto.setCreateTime( entity.getCreateTime() );

        return localFileDto;
    }

    @Override
    public List<LocalFile> toEntity(List<LocalFileDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<LocalFile> list = new ArrayList<LocalFile>( dtoList.size() );
        for ( LocalFileDto localFileDto : dtoList ) {
            list.add( toEntity( localFileDto ) );
        }

        return list;
    }

    @Override
    public List<LocalFileDto> toDto(List<LocalFile> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LocalFileDto> list = new ArrayList<LocalFileDto>( entityList.size() );
        for ( LocalFile localFile : entityList ) {
            list.add( toDto( localFile ) );
        }

        return list;
    }
}
