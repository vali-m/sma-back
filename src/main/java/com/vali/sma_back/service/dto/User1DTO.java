package com.vali.sma_back.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the User1 entity.
 */
public class User1DTO implements Serializable {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private Long karma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getKarma() {
        return karma;
    }

    public void setKarma(Long karma) {
        this.karma = karma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User1DTO user1DTO = (User1DTO) o;
        if (user1DTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), user1DTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User1DTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", karma=" + getKarma() +
            "}";
    }
}
