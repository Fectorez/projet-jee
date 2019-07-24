package com.esgi.projetjee.service.dto;

import com.esgi.projetjee.domain.Event;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class EventDto implements Serializable {

    private Integer id;
    private String name;
    private String date; // yyyy-mm-dd
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id) &&
                name.equals(eventDto.name) &&
                Objects.equals(date, eventDto.date) &&
                Objects.equals(location, eventDto.location) &&
                Objects.equals(userId, eventDto.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, location, userId);
    }
}
