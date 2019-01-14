package com.vali.sma_back.repository;

import com.vali.sma_back.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * Spring Data  repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("select t \n" +
        "from Topic t \n" +
        "where acos(sin(:coordX)*sin(radians(t.coordX)) + \n" +
        "   cos(:coordX)*cos(radians(t.coordX))*cos(radians(t.coordY)-:coordY))*6378.1 < :dist")
    List<Topic> findLocal(@Param(value="coordX") Double coordX,
                          @Param(value="coordY") Double coordY,
                          @Param(value="dist") Double dist);

    List<Topic> findTopicByCity(String city);

}
