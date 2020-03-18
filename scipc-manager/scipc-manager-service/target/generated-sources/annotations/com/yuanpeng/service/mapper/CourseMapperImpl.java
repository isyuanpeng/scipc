package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.CourseDto;
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
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course toEntity(CourseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( dto.getId() );
        course.setCode( dto.getCode() );
        course.setName( dto.getName() );
        course.setChapterCount( dto.getChapterCount() );
        course.setUrl( dto.getUrl() );
        course.setCredit( dto.getCredit() );
        course.setIsRequired( dto.getIsRequired() );
        course.setRemark( dto.getRemark() );
        course.setCourseType( dto.getCourseType() );
        course.setStatus( dto.getStatus() );
        course.setCreateTime( dto.getCreateTime() );
        course.setUpdateTime( dto.getUpdateTime() );

        return course;
    }

    @Override
    public CourseDto toDto(Course entity) {
        if ( entity == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setId( entity.getId() );
        courseDto.setCode( entity.getCode() );
        courseDto.setName( entity.getName() );
        courseDto.setChapterCount( entity.getChapterCount() );
        courseDto.setUrl( entity.getUrl() );
        courseDto.setCredit( entity.getCredit() );
        courseDto.setIsRequired( entity.getIsRequired() );
        courseDto.setRemark( entity.getRemark() );
        courseDto.setCourseType( entity.getCourseType() );
        courseDto.setStatus( entity.getStatus() );
        courseDto.setCreateTime( entity.getCreateTime() );
        courseDto.setUpdateTime( entity.getUpdateTime() );

        return courseDto;
    }

    @Override
    public List<Course> toEntity(List<CourseDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Course> list = new ArrayList<Course>( dtoList.size() );
        for ( CourseDto courseDto : dtoList ) {
            list.add( toEntity( courseDto ) );
        }

        return list;
    }

    @Override
    public List<CourseDto> toDto(List<Course> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourseDto> list = new ArrayList<CourseDto>( entityList.size() );
        for ( Course course : entityList ) {
            list.add( toDto( course ) );
        }

        return list;
    }
}
