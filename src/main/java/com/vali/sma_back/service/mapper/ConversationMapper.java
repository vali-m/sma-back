package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.Conversation;
import com.vali.sma_back.service.dto.ConversationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Conversation and its DTO ConversationDTO.
 */
@Mapper(componentModel = "spring", uses = {TopicMapper.class, UserMapper.class, MessageMapper.class})
public interface ConversationMapper extends EntityMapper<ConversationDTO, Conversation> {

    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "respondingUser.id", target = "respondingUserId")
    @Mapping(source = "messages", target = "messages")
    ConversationDTO toDto(Conversation conversation);

    @Mapping(source = "topicId", target = "topic")
    @Mapping(source = "respondingUserId", target = "respondingUser")
    @Mapping(source = "messages", target = "messages")
    Conversation toEntity(ConversationDTO conversationDTO);

    default Conversation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conversation conversation = new Conversation();
        conversation.setId(id);
        return conversation;
    }
}
