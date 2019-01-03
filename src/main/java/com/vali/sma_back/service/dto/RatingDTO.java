package com.vali.sma_back.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Rating entity.
 */
public class RatingDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = -1)
    @Max(value = 1)
    private Integer score;

    private Long userId;

    private Long topicId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RatingDTO ratingDTO = (RatingDTO) o;
        if (ratingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ratingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", user=" + getUserId() +
            ", topic=" + getTopicId() +
            "}";
    }
}
