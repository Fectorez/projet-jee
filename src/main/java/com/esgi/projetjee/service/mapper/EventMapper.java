package com.esgi.projetjee.service.mapper;

import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.service.dto.EventDto;
import org.mapstruct.*;

@Mapper(uses = { EventDto.class} , componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "user.id", target = "userId"),
    })
    EventDto eventToEventDto(Event event);

    @InheritInverseConfiguration
    Event eventDtoToEvent(EventDto eventDto);
}
