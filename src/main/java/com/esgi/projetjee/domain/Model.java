package com.esgi.projetjee.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@MappedSuperclass
@Getter
@ToString
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
