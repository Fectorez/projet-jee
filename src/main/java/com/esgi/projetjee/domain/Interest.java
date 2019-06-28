package com.esgi.projetjee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Objects;

@ToString(callSuper = true)
@Entity
public class Interest extends Model{

    private String name;

    @ManyToMany(mappedBy = "interests")
    @JsonIgnore
    private Collection<User> users;

    @ManyToMany(mappedBy = "interests")
    @JsonIgnore
    private Collection<Event> events;

    public Interest(String name, Collection<User> users, Collection<Event> events) {
        this.name = name;
        this.users = users;
        this.events = events;
    }

    public Interest() {
    }

    public Interest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interest interest = (Interest) o;
        return name.equals(interest.name) &&
                Objects.equals(users, interest.users) &&
                Objects.equals(events, interest.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users, events);
    }
}
