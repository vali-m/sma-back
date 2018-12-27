package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.*;
import com.vali.sma_back.service.dto.TopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Topic and its DTO TopicDTO.
 */
@Mapper(componentModel = "spring", uses = {User1Mapper.class})
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {

    @Mapping(source = "user1.id", target = "user1Id")
    TopicDTO toDto(Topic topic);

    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(source = "user1Id", target = "user1")
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
