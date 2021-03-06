package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.Topic;
import com.vali.sma_back.service.dto.TopicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Topic and its DTO TopicDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, MessageMapper.class})
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {

    @Mapping(source = "user", target = "postedBy")
    @Mapping(source = "message", target = "message")
    TopicDTO toDto(Topic topic);

    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "message", target = "message")
    Topic toEntity(TopicDTO topicDTO);

    default Topic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Topic topic = new Topic();
        topic.setId(id);
        return topic;
    }
}
