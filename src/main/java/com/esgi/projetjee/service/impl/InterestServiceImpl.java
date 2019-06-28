package com.esgi.projetjee.service.impl;

import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.service.InterestService;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("InterestService")
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final EventRepository eventRepository;
    private final InterestMapper interestMapper;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository, EventRepository eventRepository, InterestMapper interestMapper, EventMapper eventMapper, UserMapper userMapper) {
        this.interestRepository = interestRepository;
        this.eventRepository = eventRepository;
        this.interestMapper = interestMapper;
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InterestDto> findAll() {
        return interestRepository.findAll().stream().map(interestMapper::interestToInterestDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Optional<InterestDto> findOne(Integer id) {
        return interestRepository.findById(id).map(interestMapper::interestToInterestDto);
    }

    @Override
    public void delete(Integer id) {
        interestRepository.deleteById(id);
    }

    @Override
    public InterestDto create(InterestDto interestDto) {
        Interest interest = interestMapper.interestDtoToInterest(interestDto);
        interest = interestRepository.save(interest);
        return interestMapper.interestToInterestDto(interest);
    }

    @Override
    public List<EventDto> findByIdEvents(Integer id) throws PrendPlaceException {
        Optional<Interest> interest = interestRepository.findById(id);
        if ( !interest.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Interest not found");
        }
        Collection<Event> events = interest.get().getEvents();
        return events.stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findByIdUsers(Integer id) throws PrendPlaceException {
        Optional<Interest> interest = interestRepository.findById(id);
        if ( !interest.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        Collection<User> users = interest.get().getUsers();
        return users.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public InterestDto addEvent(Integer id, Integer fk) throws PrendPlaceException {
        Optional<Interest> optionalInterest = interestRepository.findById(id);
        if ( !optionalInterest.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Interest interest = optionalInterest.get();

        Optional<Event> optionalEvent = eventRepository.findById(fk);
        if ( !optionalEvent.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Interest not found");
        }
        Event event = optionalEvent.get();

        Collection<Event> events = interest.getEvents();
        events.add(event);
        interest = interestRepository.save(interest);
        return interestMapper.interestToInterestDto(interest);
    }

    /*@Transactional
    public Interest createOrUpdateInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    @Transactional
    public Interest updateInterestById(Integer id) {
        Optional<Interest> optional = interestRepository.findById(id);
        if ( !optional.isPresent() ) {
            throw new ResourceNotFoundException("No interest found with id " + id);
        }
        Interest interest = optional.get();
        return interestRepository.save(interest);
    }

    public void deleteInterestById(Integer id) {
        interestRepository.deleteById(id);
    }*/
}
