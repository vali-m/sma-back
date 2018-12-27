package com.vali.sma_back.service.mapper;

import com.vali.sma_back.domain.*;
import com.vali.sma_back.service.dto.User1DTO;

import org.mapstruct.*;

/**
 * Mapper for the entity User1 and its DTO User1DTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface User1Mapper extends EntityMapper<User1DTO, User1> {


    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "message", ignore = true)
    @Mapping(target = "rating", ignore = true)
    User1 toEntity(User1DTO user1DTO);

    default User1 fromId(Long id) {
        if (id == null) {
            return null;
        }
        User1 user1 = new User1();
        user1.setId(id);
        return user1;
    }
}
