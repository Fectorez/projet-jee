package com.esgi.projetjee.service;

import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final InterestRepository interestRepository;

    @Autowired
    public EventService(EventRepository eventRepository, InterestRepository interestRepository) {
        this.eventRepository = eventRepository;
        this.interestRepository = interestRepository;
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
    public Event createOrUpdateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Transactional
    public Event updateEventById(Integer id) {
        Optional<Event> optional = eventRepository.findById(id);
        if ( !optional.isPresent() ) {
            throw new ResourceNotFoundException("No event found with id " + id);
        }
        Event event = optional.get();
        return eventRepository.save(event);
    }

    @Transactional
    public Event updateEventByIdInterests(Integer id, Integer fk) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if ( !optionalEvent.isPresent() ) {
            throw new ResourceNotFoundException("No event found with id " + id);
        }
        Event event = optionalEvent.get();
        if ( event.getInterests() == null ) {
            event.setInterests(new HashSet<>());
        }

        Optional<Interest> optionalInterest = interestRepository.findById(id);
        if ( !optionalInterest.isPresent() ) {
            throw new ResourceNotFoundException("No interest found with id " + id);
        }
        Interest interest = optionalInterest.get();

        Collection<Interest> updatedInterests = event.getInterests();
        updatedInterests.add(interest);
        event.setInterests(updatedInterests);

        return eventRepository.save(event);
    }

    public void deleteEventById(Integer id) {
        eventRepository.deleteById(id);
    }
}
