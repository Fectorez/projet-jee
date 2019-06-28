package com.esgi.projetjee.service.dto;

import com.esgi.projetjee.domain.Interest;

import java.io.Serializable;

public class InterestDto implements Serializable {

    private Integer id;
    private String name;

    public InterestDto() {
    }

    public InterestDto(Interest interest) {
        this.id = interest.getId();
        this.name = interest.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
