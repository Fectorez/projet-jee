package com.esgi.projetjee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Event extends Model {

    private String name;
    private Date date;
    private String location;
    @ManyToOne
    private User user;

    @ManyToMany
    private Collection<Interest> interests;

    public Collection<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<User> participants) {
        this.participants = participants;
    }

    @ManyToMany(mappedBy = "partEvents")
    @JsonIgnore
    private Collection<User> participants;

    public Event(String name, Date date, String location, User user, Collection<Interest> interests, Collection<User> participants) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.user = user;
        this.interests = interests;
        this.participants = participants;
    }

    public Event(String name, Date date, String location, User user) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.user = user;
    }

    public Event(String name, Date date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public Event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Collection<Interest> interests) {
        this.interests = interests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return name.equals(event.name) &&
                Objects.equals(date, event.date) &&
                Objects.equals(location, event.location) &&
                Objects.equals(user, event.user) &&
                Objects.equals(interests, event.interests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, location, user, interests);
    }
}
