package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.CourseSmallDto;
import com.yuanpeng.entity.Course;
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
public class CourseSmallMapperImpl implements CourseSmallMapper {

    @Override
    public Course toEntity(CourseSmallDto dto) {
        if ( dto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( dto.getId() );
        course.setName( dto.getName() );

        return course;
    }

    @Override
    public CourseSmallDto toDto(Course entity) {
        if ( entity == null ) {
            return null;
        }

        CourseSmallDto courseSmallDto = new CourseSmallDto();

        courseSmallDto.setId( entity.getId() );
        courseSmallDto.setName( entity.getName() );

        return courseSmallDto;
    }

    @Override
    public List<Course> toEntity(List<CourseSmallDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Course> list = new ArrayList<Course>( dtoList.size() );
        for ( CourseSmallDto courseSmallDto : dtoList ) {
            list.add( toEntity( courseSmallDto ) );
        }

        return list;
    }

    @Override
    public List<CourseSmallDto> toDto(List<Course> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourseSmallDto> list = new ArrayList<CourseSmallDto>( entityList.size() );
        for ( Course course : entityList ) {
            list.add( toDto( course ) );
        }

        return list;
    }
}
