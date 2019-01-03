package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.Rating;
import com.vali.sma_back.service.dto.RatingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Rating and its DTO RatingDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, TopicMapper.class})
public interface RatingMapper extends EntityMapper<RatingDTO, Rating> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "topic.id", target = "topicId")
    RatingDTO toDto(Rating rating);

    @Mapping(source = "userId", target = "user")
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
