package com.vali.sma_back.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Topic entity.
 */
public class TopicDTO implements Serializable {

    private Long id;

    private @javax.validation.constraints.Min(-90) @javax.validation.constraints.Max(90) Double coordX;

    private Double coordY;

    private Boolean archived;

    private Integer score;

    private String city;

    private String title;

    private Integer myScore;

    private UserDTO user;

    private MessageDTO message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @javax.validation.constraints.Min(-90) @javax.validation.constraints.Max(90) Double getCoordX() {
        return coordX;
    }

    public void setCoordX(@javax.validation.constraints.Min(-90) @javax.validation.constraints.Max(90) Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMyScore() {
        return myScore;
    }

    public void setMyScore(Integer myScore) {
        this.myScore = myScore;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TopicDTO topicDTO = (TopicDTO) o;
        if (topicDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), topicDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TopicDTO{" +
            "id=" + getId() +
            ", coordX=" + getCoordX() +
            ", coordY=" + getCoordY() +
            ", archived='" + isArchived() + "'" +
            ", score=" + getScore() +
            ", myScore=" + getMyScore() +
            ", city='" + getCity() + "'" +
            ", user= " + getUser() +
            ", message= " + getMessage() +
            "}";
    }

}
