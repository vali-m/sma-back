package com.vali.sma_back.service;

import com.vali.sma_back.domain.User1;
import com.vali.sma_back.repository.User1Repository;
import com.vali.sma_back.service.dto.User1DTO;
import com.vali.sma_back.service.mapper.User1Mapper;
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
 * Service Implementation for managing User1.
 */
@Service
@Transactional
public class User1Service {

    private final Logger log = LoggerFactory.getLogger(User1Service.class);

    private final User1Repository user1Repository;

    private final User1Mapper user1Mapper;

    public User1Service(User1Repository user1Repository, User1Mapper user1Mapper) {
        this.user1Repository = user1Repository;
        this.user1Mapper = user1Mapper;
    }

    /**
     * Save a user1.
     *
     * @param user1DTO the entity to save
     * @return the persisted entity
     */
    public User1DTO save(User1DTO user1DTO) {
        log.debug("Request to save User1 : {}", user1DTO);

        User1 user1 = user1Mapper.toEntity(user1DTO);
        user1 = user1Repository.save(user1);
        return user1Mapper.toDto(user1);
    }

    /**
     * Get all the user1S.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<User1DTO> findAll() {
        log.debug("Request to get all User1S");
        return user1Repository.findAll().stream()
            .map(user1Mapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the user1S where Message is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<User1DTO> findAllWhereMessageIsNull() {
        log.debug("Request to get all user1S where Message is null");
        return StreamSupport
            .stream(user1Repository.findAll().spliterator(), false)
            .filter(user1 -> user1.getMessage() == null)
            .map(user1Mapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the user1S where Rating is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<User1DTO> findAllWhereRatingIsNull() {
        log.debug("Request to get all user1S where Rating is null");
        return StreamSupport
            .stream(user1Repository.findAll().spliterator(), false)
            .filter(user1 -> user1.getRating() == null)
            .map(user1Mapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one user1 by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<User1DTO> findOne(Long id) {
        log.debug("Request to get User1 : {}", id);
        return user1Repository.findById(id)
            .map(user1Mapper::toDto);
    }

    /**
     * Delete the user1 by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete User1 : {}", id);
        user1Repository.deleteById(id);
    }
}
