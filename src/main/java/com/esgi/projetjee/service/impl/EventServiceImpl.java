package com.esgi.projetjee.service.impl;

import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.repository.UserRepository;
import com.esgi.projetjee.service.EventService;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.dto.UserDto;
import com.esgi.projetjee.service.mapper.EventMapper;
import com.esgi.projetjee.service.mapper.InterestMapper;
import com.esgi.projetjee.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("EventService")
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final InterestRepository interestRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;
    private final InterestMapper interestMapper;
    private final UserMapper userMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, InterestRepository interestRepository, EventMapper eventMapper, InterestMapper interestMapper, UserRepository userRepository, UserMapper userMapper) {
        this.eventRepository = eventRepository;
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
        this.eventMapper = eventMapper;
        this.interestMapper = interestMapper;
        this.userMapper= userMapper;
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

    @Override
    public void delete(Integer id) {
        eventRepository.deleteById(id);
    }

    @Override
    public EventDto create(EventDto eventDto) {
        Event event = eventMapper.eventDtoToEvent(eventDto);
        event = eventRepository.save(event);
        return eventMapper.eventToEventDto(event);
    }

    @Override
    public List<InterestDto> findByIdInterests(Integer id) throws PrendPlaceException {
        Optional<Event> event = eventRepository.findById(id);
        if ( !event.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Collection<Interest> interests = event.get().getInterests();
        return interests.stream().map(interestMapper::interestToInterestDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findByIdParticipants(Integer id) throws PrendPlaceException {
        Optional<Event> event = eventRepository.findById(id);
        if ( !event.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Collection<User> participants = event.get().getParticipants();
        return participants.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public EventDto addInterest(Integer id, Integer fk) throws PrendPlaceException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if ( !optionalEvent.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Event event = optionalEvent.get();

        Optional<Interest> optionalInterest = interestRepository.findById(fk);
        if ( !optionalInterest.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Interest not found");
        }
        Interest interest = optionalInterest.get();

        Collection<Interest> interests = event.getInterests();
        interests.add(interest);
        event = eventRepository.save(event);
        return eventMapper.eventToEventDto(event);

    }

    @Override
    public EventDto addParticipant(Integer id, Integer fk) throws PrendPlaceException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if ( !optionalEvent.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Event event = optionalEvent.get();

        Optional<User> optionalUser = userRepository.findById(fk);
        if ( !optionalUser.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        User participant = optionalUser.get();

        Collection<User> participants = event.getParticipants();
        participants.add(participant);
        event = eventRepository.save(event);
        return eventMapper.eventToEventDto(event);
    }

    /*private List<Interest> findInterestsByName(String... interestsNames) {
        List<Interest> interests = new LinkedList<>();
        for (String interestName : interestsNames) {
            Optional<Interest> interest = interestRepository.findByName(interestName);
            interest.ifPresent(interests::add);
        }
        return interests;
    }*/

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
