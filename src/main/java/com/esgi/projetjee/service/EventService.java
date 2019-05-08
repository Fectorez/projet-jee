package com.esgi.projetjee.service;

import com.esgi.projetjee.config.EventConfig;
import com.esgi.projetjee.dao.EventRepository;
import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventConfig eventConfig;

    @Autowired
    public EventService(EventRepository eventRepository, EventConfig eventConfig) {
        this.eventRepository = eventRepository;
        this.eventConfig = eventConfig;
    }

    @Transactional(readOnly = true)
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Event getEvent(Integer id) {
        Optional<Event> optional = eventRepository.findById(id);
        if ( !optional.isPresent() ) {
            throw new ResourceNotFoundException("No event found with id " + id);
        }
        return optional.get();
    }

    @Transactional
    public boolean saveEvent(Event event) {
        eventRepository.save(event);
        return true;
    }

    public boolean deleteEvent(Integer id) {
        eventRepository.deleteById(id);
        return true;
    }
}
