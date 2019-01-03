package com.vali.sma_back.repository;

import com.vali.sma_back.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Conversation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
