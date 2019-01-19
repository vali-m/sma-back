package com.vali.sma_back.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Conversation entity.
 */
public class ConversationDTO implements Serializable {

    private Long id;

    private Long topicId;

    private Long respondingUserId;

    private Set<MessageDTO> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getRespondingUserId() {
        return respondingUserId;
    }

    public void setRespondingUserId(Long user1Id) {
        this.respondingUserId = user1Id;
    }

    public Set<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTO> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConversationDTO conversationDTO = (ConversationDTO) o;
        if (conversationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conversationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConversationDTO{" +
            "id=" + getId() +
            ", topic=" + getTopicId() +
            ", respondingUser=" + getRespondingUserId() +
            ", messages=" + getMessages() +
            "}";
    }
}
