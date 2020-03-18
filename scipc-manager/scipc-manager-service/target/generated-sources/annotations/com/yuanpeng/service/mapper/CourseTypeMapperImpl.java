package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.CourseSmallDto;
import com.yuanpeng.dto.CourseTypeDto;
import com.yuanpeng.entity.Course;
import com.yuanpeng.entity.CourseType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-17T14:25:24+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class CourseTypeMapperImpl implements CourseTypeMapper {

    @Autowired
    private CourseSmallMapper courseSmallMapper;

    @Override
    public CourseType toEntity(CourseTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        CourseType courseType = new CourseType();

        courseType.setId( dto.getId() );
        courseType.setName( dto.getName() );
        courseType.setRemark( dto.getRemark() );
        courseType.setCourses( courseSmallDtoSetToCourseSet( dto.getCourses() ) );
        courseType.setCreateTime( dto.getCreateTime() );
        courseType.setUpdateTime( dto.getUpdateTime() );

        return courseType;
    }

    @Override
    public CourseTypeDto toDto(CourseType entity) {
        if ( entity == null ) {
            return null;
        }

        CourseTypeDto courseTypeDto = new CourseTypeDto();

        courseTypeDto.setId( entity.getId() );
        courseTypeDto.setName( entity.getName() );
        courseTypeDto.setRemark( entity.getRemark() );
        courseTypeDto.setCourses( courseSetToCourseSmallDtoSet( entity.getCourses() ) );
        courseTypeDto.setCreateTime( entity.getCreateTime() );
        courseTypeDto.setUpdateTime( entity.getUpdateTime() );

        return courseTypeDto;
    }

    @Override
    public List<CourseType> toEntity(List<CourseTypeDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CourseType> list = new ArrayList<CourseType>( dtoList.size() );
        for ( CourseTypeDto courseTypeDto : dtoList ) {
            list.add( toEntity( courseTypeDto ) );
        }

        return list;
    }

    @Override
    public List<CourseTypeDto> toDto(List<CourseType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourseTypeDto> list = new ArrayList<CourseTypeDto>( entityList.size() );
        for ( CourseType courseType : entityList ) {
            list.add( toDto( courseType ) );
        }

        return list;
    }

    protected Set<Course> courseSmallDtoSetToCourseSet(Set<CourseSmallDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Course> set1 = new HashSet<Course>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CourseSmallDto courseSmallDto : set ) {
            set1.add( courseSmallMapper.toEntity( courseSmallDto ) );
        }

        return set1;
    }

    protected Set<CourseSmallDto> courseSetToCourseSmallDtoSet(Set<Course> set) {
        if ( set == null ) {
            return null;
        }

        Set<CourseSmallDto> set1 = new HashSet<CourseSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Course course : set ) {
            set1.add( courseSmallMapper.toDto( course ) );
        }

        return set1;
    }
}
