package com.vali.sma_back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Topic.
 */
@Entity
@Table(name = "topic")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "coord_x")
    private Long coordX;

    @Column(name = "coord_y")
    private Long coordY;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "score")
    private Integer score;

    @OneToOne(mappedBy = "topic")
    @JsonIgnore
    private Conversation conversation;

    @OneToOne(mappedBy = "topic")
    @JsonIgnore
    private Rating rating;

    @ManyToOne
    @JsonIgnoreProperties("topics")
    private User1 user1;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoordX() {
        return coordX;
    }

    public Topic coordX(Long coordX) {
        this.coordX = coordX;
        return this;
    }

    public void setCoordX(Long coordX) {
        this.coordX = coordX;
    }

    public Long getCoordY() {
        return coordY;
    }

    public Topic coordY(Long coordY) {
        this.coordY = coordY;
        return this;
    }

    public void setCoordY(Long coordY) {
        this.coordY = coordY;
    }

    public Boolean isArchived() {
        return archived;
    }

    public Topic archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Integer getScore() {
        return score;
    }

    public Topic score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Topic conversation(Conversation conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Rating getRating() {
        return rating;
    }

    public Topic rating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public User1 getUser1() {
        return user1;
    }

    public Topic user1(User1 user1) {
        this.user1 = user1;
        return this;
    }

    public void setUser1(User1 user1) {
        this.user1 = user1;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        if (topic.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), topic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Topic{" +
            "id=" + getId() +
            ", coordX=" + getCoordX() +
            ", coordY=" + getCoordY() +
            ", archived='" + isArchived() + "'" +
            ", score=" + getScore() +
            "}";
    }
}
