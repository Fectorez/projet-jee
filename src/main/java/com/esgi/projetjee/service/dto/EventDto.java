package com.esgi.projetjee.service.dto;

import com.esgi.projetjee.domain.Event;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EventDto implements Serializable {

    private Integer id;
    private String name;
    private String date;
    private String location;
    private Integer userId;

    public EventDto() {
    }

    public EventDto(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        this.date = dateFormat.format(event.getDate());
        this.location = event.getLocation();
        this.userId = event.getUser().getId();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
