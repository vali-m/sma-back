package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.Topic;
import com.vali.sma_back.service.dto.TopicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Topic and its DTO TopicDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {

    @Mapping(source = "user.id", target = "userId")
    TopicDTO toDto(Topic topic);

    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(source = "userId", target = "user")
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
