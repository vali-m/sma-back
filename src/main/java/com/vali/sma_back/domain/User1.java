package com.vali.sma_back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A User1.
 */
@Entity
@Table(name = "user_1")
public class User1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Column(name = "karma", nullable = false)
    private Long karma;

    @OneToMany(mappedBy = "user1")
    private Set<Topic> topics = new HashSet<>();
    @OneToOne(mappedBy = "user1")
    @JsonIgnore
    private Message message;

    @OneToOne(mappedBy = "user1")
    @JsonIgnore
    private Rating rating;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public User1 username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getKarma() {
        return karma;
    }

    public User1 karma(Long karma) {
        this.karma = karma;
        return this;
    }

    public void setKarma(Long karma) {
        this.karma = karma;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public User1 topics(Set<Topic> topics) {
        this.topics = topics;
        return this;
    }

    public User1 addTopic(Topic topic) {
        this.topics.add(topic);
        topic.setUser1(this);
        return this;
    }

    public User1 removeTopic(Topic topic) {
        this.topics.remove(topic);
        topic.setUser1(null);
        return this;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Message getMessage() {
        return message;
    }

    public User1 message(Message message) {
        this.message = message;
        return this;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Rating getRating() {
        return rating;
    }

    public User1 rating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
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
        User1 user1 = (User1) o;
        if (user1.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), user1.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User1{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", karma=" + getKarma() +
            "}";
    }
}
