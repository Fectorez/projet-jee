package com.esgi.projetjee.model;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)

@Entity
public class Event extends Model {

    @Property
    private String name;

    @Property
    private Date date;

    @Property
    private String location;
}
