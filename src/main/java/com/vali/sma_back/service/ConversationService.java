package com.vali.sma_back.service;

import com.vali.sma_back.domain.Conversation;
import com.vali.sma_back.domain.User;
import com.vali.sma_back.repository.ConversationRepository;
import com.vali.sma_back.repository.UserRepository;
import com.vali.sma_back.security.SecurityUtils;
import com.vali.sma_back.service.dto.ConversationDTO;
import com.vali.sma_back.service.mapper.ConversationMapper;
import com.vali.sma_back.web.rest.errors.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Conversation.
 */
@Service
@Transactional
public class ConversationService {

    private final Logger log = LoggerFactory.getLogger(ConversationService.class);

    private final ConversationRepository conversationRepository;

    private final ConversationMapper conversationMapper;

    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, ConversationMapper conversationMapper, UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.conversationMapper = conversationMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a conversation.
     *
     * @param conversationDTO the entity to save
     * @return the persisted entity
     */
    public ConversationDTO save(ConversationDTO conversationDTO) {
        Optional<String> userLogin = SecurityUtils.getCurrentUserLogin();
        log.debug("Request from {} to save Conversation : {}", userLogin, conversationDTO);
        Long userId = userLogin
            .map(userRepository::findOneByLogin)
            .orElseThrow(() -> new InternalServerErrorException("Could not find User"))
            .map(User::getId)
            .orElseThrow(() -> new InternalServerErrorException("Could not find User"));

        conversationDTO.setRespondingUserId(userId);
        Conversation conversation = conversationMapper.toEntity(conversationDTO);
        conversation = conversationRepository.save(conversation);
        return conversationMapper.toDto(conversation);
    }

    /**
     * Get all the conversations.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ConversationDTO> findAll() {
        log.debug("Request to get all Conversations");
        return conversationRepository.findAll().stream()
            .map(conversationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<ConversationDTO> findMine(String username){
        log.debug("Request to find all Conversations of user {}", username);
        return conversationRepository.findMine(username)
            .stream()
            .map(conversationMapper::toDto)
            .collect(Collectors.toList());

    }

    /**
     * Get one conversation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ConversationDTO> findOne(Long id) {
        log.debug("Request to get Conversation : {}", id);
        return conversationRepository.findById(id)
            .map(conversationMapper::toDto);
    }

    /**
     * Delete the conversation by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Conversation : {}", id);
        conversationRepository.deleteById(id);
    }
}
