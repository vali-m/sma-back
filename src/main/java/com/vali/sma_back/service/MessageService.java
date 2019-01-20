package com.vali.sma_back.service;

import com.vali.sma_back.domain.Message;
import com.vali.sma_back.domain.User;
import com.vali.sma_back.repository.MessageRepository;
import com.vali.sma_back.repository.UserRepository;
import com.vali.sma_back.security.SecurityUtils;
import com.vali.sma_back.service.dto.MessageDTO;
import com.vali.sma_back.service.mapper.MessageMapper;
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
 * Service Implementation for managing Message.
 */
@Service
@Transactional
public class MessageService {

    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a message.
     *
     * @param messageDTO the entity to save
     * @return the persisted entity
     */
    public MessageDTO save(MessageDTO messageDTO) {
        if(messageDTO.getConversationId() == null){
            throw new InternalServerErrorException("Posted messages require a conversationId!");
        }
        log.debug("Request to save Message : {}", messageDTO);
        Long userId = SecurityUtils.getCurrentUserLogin()
            .map(userRepository::findOneByLogin)
            .orElseThrow(() -> new InternalServerErrorException("Could not find user with this name"))
            .map(User::getId)
            .orElseThrow(() -> new InternalServerErrorException("Could not find user Username"));

        messageDTO.setUserId(userId);

        Message message = messageMapper.toEntity(messageDTO);
        message = messageRepository.save(message);
        return messageMapper.toDto(message);
    }

    /**
     * Get all the messages.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MessageDTO> findAll() {
        log.debug("Request to get all Messages");
        return messageRepository.findAll().stream()
            .map(messageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one message by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MessageDTO> findOne(Long id) {
        log.debug("Request to get Message : {}", id);
        return messageRepository.findById(id)
            .map(messageMapper::toDto);
    }

    /**
     * Delete the message by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Message : {}", id);
        messageRepository.deleteById(id);
    }
}
