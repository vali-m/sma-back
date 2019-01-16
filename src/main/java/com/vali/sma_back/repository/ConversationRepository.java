package com.vali.sma_back.repository;

import com.vali.sma_back.domain.Conversation;
import com.vali.sma_back.service.dto.ConversationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Conversation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("select c from Conversation c \n " +
        "where c.respondingUser.login = :username \n " +
        "or c.topic.user.login = :username")
    List<Conversation> findMine(@Param("username") String username);

}
