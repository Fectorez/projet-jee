package com.esgi.projetjee.web;

import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.EventService;
import com.esgi.projetjee.service.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventResource {

    private final EventService eventService;

    @Autowired
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDto findOne(@PathVariable Integer id) throws PrendPlaceException {
        Optional<EventDto> event = eventService.findOne(id);
        if ( event.isPresent() ) {
            return event.get();
        }
        else {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
    }

    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event) {
        return eventService.createOrUpdateEvent(event);
    }

    @PutMapping
    public Event updateEvents(@RequestBody Event event) {
        return eventService.createOrUpdateEvent(event);
    }

    @PutMapping("{id}/interests/{fk}")
    public Event updateByIdInterests(@PathVariable Integer id, @PathVariable Integer fk) {
        return eventService.updateEventByIdInterests(id, fk);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Integer id) {
        return eventService.updateEventById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.deleteEventById(id);
    }*/
}
