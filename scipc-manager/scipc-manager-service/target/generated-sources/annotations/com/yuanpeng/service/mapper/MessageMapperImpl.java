package com.yuanpeng.service.mapper;

import com.yuanpeng.dto.MessageDto;
import com.yuanpeng.entity.Message;
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
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message toEntity(MessageDto dto) {
        if ( dto == null ) {
            return null;
        }

        Message message = new Message();

        if ( dto.getId() != null ) {
            message.setId( dto.getId().longValue() );
        }
        message.setSender( dto.getSender() );
        message.setReciver( dto.getReciver() );
        message.setIsRead( dto.getIsRead() );
        message.setContent( dto.getContent() );
        message.setType( dto.getType() );
        message.setCreateTime( dto.getCreateTime() );

        return message;
    }

    @Override
    public MessageDto toDto(Message entity) {
        if ( entity == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        if ( entity.getId() != null ) {
            messageDto.setId( entity.getId().intValue() );
        }
        messageDto.setSender( entity.getSender() );
        messageDto.setReciver( entity.getReciver() );
        messageDto.setIsRead( entity.getIsRead() );
        messageDto.setContent( entity.getContent() );
        messageDto.setType( entity.getType() );
        messageDto.setCreateTime( entity.getCreateTime() );

        return messageDto;
    }

    @Override
    public List<Message> toEntity(List<MessageDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Message> list = new ArrayList<Message>( dtoList.size() );
        for ( MessageDto messageDto : dtoList ) {
            list.add( toEntity( messageDto ) );
        }

        return list;
    }

    @Override
    public List<MessageDto> toDto(List<Message> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( entityList.size() );
        for ( Message message : entityList ) {
            list.add( toDto( message ) );
        }

        return list;
    }
}
