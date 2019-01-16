package com.vali.sma_back.repository;

import com.vali.sma_back.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Rating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select r.score from Rating r \n" +
        "where r.topic.id = :topicId \n " +
        "and r.user.login = :username")
    Optional<Integer> findByTopicIdAndUserId(@Param("topicId") Long topicId,
                                            @Param("username") String username);

}
