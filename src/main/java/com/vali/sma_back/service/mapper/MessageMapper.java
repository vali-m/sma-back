package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.Message;
import com.vali.sma_back.service.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ConversationMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "conversation.id", target = "conversationId")
    MessageDTO toDto(Message message);

    @Mapping(source = "userId", target = "user")
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
