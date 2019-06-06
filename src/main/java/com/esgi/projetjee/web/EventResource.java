package com.esgi.projetjee.web;

import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventResource {

    private final EventService eventService;

    @Autowired
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Integer id){
        return eventService.getEvent(id);
    }

    @PostMapping
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
    }
}
