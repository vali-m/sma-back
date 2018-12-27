package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.*;
import com.vali.sma_back.service.dto.MessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {User1Mapper.class, ConversationMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "user1.id", target = "user1Id")
    @Mapping(source = "conversation.id", target = "conversationId")
    MessageDTO toDto(Message message);

    @Mapping(source = "user1Id", target = "user1")
    @Mapping(source = "conversationId", target = "conversation")
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
