package com.esgi.projetjee.model;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
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

    @Property
    private String name;

    @Property
    private Date date;

    @Property
    private String location;

    @ManyToOne
    private User user;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return name.equals(event.name) &&
                Objects.equals(date, event.date) &&
                Objects.equals(location, event.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, location);
    }
}
