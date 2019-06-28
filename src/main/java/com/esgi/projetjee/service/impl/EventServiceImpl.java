package com.esgi.projetjee.service.impl;

import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.service.EventService;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("EventService")
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> findAll() {
        return eventRepository.findAll().stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Optional<EventDto> findOne(Integer id) {
        return eventRepository.findById(id).map(eventMapper::eventToEventDto);
    }

    /*@Transactional
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
    }*/
}
