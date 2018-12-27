package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.*;
import com.vali.sma_back.service.dto.RatingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Rating and its DTO RatingDTO.
 */
@Mapper(componentModel = "spring", uses = {User1Mapper.class, TopicMapper.class})
public interface RatingMapper extends EntityMapper<RatingDTO, Rating> {

    @Mapping(source = "user1.id", target = "user1Id")
    @Mapping(source = "topic.id", target = "topicId")
    RatingDTO toDto(Rating rating);

    @Mapping(source = "user1Id", target = "user1")
    @Mapping(source = "topicId", target = "topic")
    Rating toEntity(RatingDTO ratingDTO);

    default Rating fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rating rating = new Rating();
        rating.setId(id);
        return rating;
    }
}
