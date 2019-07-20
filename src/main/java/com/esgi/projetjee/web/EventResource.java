package com.esgi.projetjee.web;

import com.esgi.projetjee.api.PhqApi;
import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.EventService;
import com.esgi.projetjee.service.InterestService;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.dto.UserDto;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventResource {

    private final EventService eventService;
    private final InterestService interestService;
    private final PhqApi phqApi = new PhqApi();

    @Autowired
    public EventResource(EventService eventService, InterestService interestService) {
        this.eventService = eventService;
        this.interestService = interestService;
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

    @GetMapping("/{id}/interests")
    public List<InterestDto> findInterests(@PathVariable Integer id) throws PrendPlaceException {
        return eventService.findByIdInterests(id);
    }

    @GetMapping("/{id}/participants")
    public List<UserDto> findParticipants(@PathVariable Integer id) throws PrendPlaceException {
        return eventService.findByIdParticipants(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        eventService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(@RequestBody EventDto eventDto) {
        return eventService.create(eventDto);
    }

    @PutMapping("/{id}/interests/{fk}")
    public EventDto addInterest(@PathVariable Integer id, @PathVariable Integer fk) throws PrendPlaceException {
        return eventService.addInterest(id, fk);
    }

    @PutMapping("/{id}/participants/{fk}")
    public EventDto addParticipant(@PathVariable Integer id, @PathVariable Integer fk) throws PrendPlaceException {
        return eventService.addParticipant(id, fk);
    }


    @GetMapping("/find-on-web")
    public JSONObject findOnWeb() throws IOException {
        return phqApi.findEvents();
    }

    /*@PutMapping("{id}/interests/{fk}")
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
