package com.esgi.projetjee.service.dto;

import com.esgi.projetjee.domain.User;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Integer id;
    private String username;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
