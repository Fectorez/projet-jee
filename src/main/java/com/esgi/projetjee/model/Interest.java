package com.esgi.projetjee.model;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Interest {

    @Property
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interest interest = (Interest) o;
        return name.equals(interest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
