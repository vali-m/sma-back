package com.vali.sma_back.service.dto;

import com.vali.sma_back.domain.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SimpleUserDTO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String username;

    private Long karma;

    public SimpleUserDTO(User user) {
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
        simpleUserDTO.setId(user.getId());
        simpleUserDTO.setKarma(user.getKarma());
        simpleUserDTO.setUsername(user.getLogin());
    }

    public SimpleUserDTO() {
//        needed empty constructor
    }

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
    public String toString() {
        return "SimpleUserDTO{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", karma=" + karma +
            '}';
    }
}
