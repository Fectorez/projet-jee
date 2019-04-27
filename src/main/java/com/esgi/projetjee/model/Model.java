package com.esgi.projetjee.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

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
