package com.esgi.projetjee.service;

import com.esgi.projetjee.config.EventConfig;
import com.esgi.projetjee.dao.EventRepository;
import com.esgi.projetjee.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public boolean saveEvent(Event event) {
        eventRepository.save(event);
        return true;
    }
}
