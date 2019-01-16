package com.vali.sma_back.service;

import com.vali.sma_back.domain.Topic;
import com.vali.sma_back.repository.RatingRepository;
import com.vali.sma_back.repository.TopicRepository;
import com.vali.sma_back.security.SecurityUtils;
import com.vali.sma_back.service.dto.TopicDTO;
import com.vali.sma_back.service.mapper.TopicMapper;
import com.vali.sma_back.web.rest.errors.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Topic.
 */
@Service
@Transactional
public class TopicService {

    private final Logger log = LoggerFactory.getLogger(TopicService.class);

    private final TopicRepository topicRepository;

    private final RatingRepository ratingRepository;

    private final TopicMapper topicMapper;

    public TopicService(TopicRepository topicRepository, RatingRepository ratingRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.ratingRepository = ratingRepository;
        this.topicMapper = topicMapper;
    }

    /**
     * Save a topic.
     *
     * @param topicDTO the entity to save
     * @return the persisted entity
     */
    public TopicDTO save(TopicDTO topicDTO) {
        log.debug("Request to save Topic : {}", topicDTO);

        Topic topic = topicMapper.toEntity(topicDTO);
        topic = topicRepository.save(topic);
        return topicMapper.toDto(topic);
    }

    public TopicDTO toRatedDto(Topic topic, String username){
        TopicDTO topicDTO = topicMapper.toDto(topic);
        Integer myScore = ratingRepository.
            findByTopicIdAndUserId(topic.getId(), username)
            .orElse(0);
        topicDTO.setMyScore(myScore);
        return topicDTO;
    }

    /**
     * Get all the topics.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TopicDTO> findAll() {
        log.debug("Request to get all Topics");
        String username = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new InternalServerErrorException("No current user login found!"));
        return topicRepository.findAll().stream()
            .map(t -> toRatedDto(t, username))
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * get all the topics where Conversation is null.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TopicDTO> findAllWhereConversationIsNull() {
        log.debug("Request to get all topics where Conversation is null");
        return StreamSupport
            .stream(topicRepository.findAll().spliterator(), false)
            .filter(topic -> topic.getConversation() == null)
            .map(topicMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * get all the topics where Rating is null.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TopicDTO> findAllWhereRatingIsNull() {
        log.debug("Request to get all topics where Rating is null");
        return StreamSupport
            .stream(topicRepository.findAll().spliterator(), false)
            .filter(topic -> topic.getRatings() == null)
            .map(topicMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one topic by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TopicDTO> findOne(Long id) {
        log.debug("Request to get Topic : {}", id);
        String username = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new InternalServerErrorException("No current user login found!"));
        return topicRepository.findById(id)
            .map(t -> toRatedDto(t, username));
    }

    /**
     * Delete the topic by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Topic : {}", id);
        topicRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TopicDTO> getNearbyTopics(Double coordX, Double coordY, Double distance) {
        log.debug("Request to get topics within {}km to ({},{})", distance, coordX, coordY);
        Double radX = coordX * 0.0174533;
        Double radY = coordY * 0.0174533;
        String username = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new InternalServerErrorException("No current user login found!"));
        return topicRepository.findLocal(radX, radY, distance)
            .stream()
            .map(t -> toRatedDto(t, username))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TopicDTO> getNearbyTopics(String city) {
        log.debug("Request to get topics in city {}!", city);
        String username = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new InternalServerErrorException("No current user login found!"));
        return topicRepository.findTopicByCityAndArchivedFalse(city)
            .stream()
            .map(t -> toRatedDto(t, username))
            .collect(Collectors.toList());
    }
}
