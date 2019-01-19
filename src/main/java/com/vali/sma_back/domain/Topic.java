package com.vali.sma_back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

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

    @Min(-90) @Max(90)
    @Column(name = "coord_x")
    private Double coordX;

    @Min(-180)
    @Max(180)
    @Column(name = "coord_y")
    private Double coordY;

    @Column(name = "archived")
    @ColumnDefault("false")
    private Boolean archived;

    @Column(name = "score")
    private Integer score;

    @Column(name = "city")
    private String city;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "topic")
    @JsonIgnore
    private Set<Conversation> conversation;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="message_id")
    private Message message;

    @OneToMany(mappedBy = "topic")
    @JsonIgnore
    private Set<Rating> ratings;

    @NotNull
    @ManyToOne(cascade = {})
    @JsonIgnoreProperties("topics")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCoordX() {
        return coordX;
    }

    public Topic coordX(Double coordX) {
        this.coordX = coordX;
        return this;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public Topic coordY(Double coordY) {
        this.coordY = coordY;
        return this;
    }

    public void setCoordY(Double coordY) {
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

    public Set<Conversation> getConversation() {
        return conversation;
    }

    public Topic conversation(Set<Conversation> conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(Set<Conversation> conversation) {
        this.conversation = conversation;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Topic ratings(Set<Rating> ratings) {
        this.ratings = ratings;
        return this;
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

    public User getUser() {
        return user;
    }

    public Topic user(User user1) {
        this.user = user1;
        return this;
    }

    public void setUser(User user1) {
        this.user = user1;
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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
