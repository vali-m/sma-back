package com.vali.sma_back.repository;

import com.vali.sma_back.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
