package com.esgi.projetjee.service.impl;

import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.repository.UserRepository;
import com.esgi.projetjee.service.UserService;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.dto.UserDto;
import com.esgi.projetjee.service.mapper.EventMapper;
import com.esgi.projetjee.service.mapper.InterestMapper;
import com.esgi.projetjee.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final InterestRepository interestRepository;
    private final EventRepository eventRepository;
    private final UserMapper userMapper;
    private final EventMapper eventMapper;
    private final InterestMapper interestMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, InterestRepository interestRepository, EventRepository eventRepository, UserMapper userMapper, EventMapper eventMapper, InterestMapper interestMapper) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
        this.eventRepository = eventRepository;
        this.userMapper = userMapper;
        this.eventMapper = eventMapper;
        this.interestMapper = interestMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if ( user == null ) {
            throw new UsernameNotFoundException("No user present with username : " + username);
        }
        else {
            return user;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Optional<UserDto> findOne(Integer id) {
        return userRepository.findById(id).map(userMapper::userToUserDto);
    }

    @Override
    public List<EventDto> findByIdEvents(Integer id) throws PrendPlaceException {
        Optional<User> user = userRepository.findById(id);
        if ( !user.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Interest not found");
        }
        Collection<Event> events = user.get().getEvents();
        return events.stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
    }

    @Override
    public List<InterestDto> findByIdInterests(Integer id) throws PrendPlaceException {
        Optional<User> user = userRepository.findById(id);
        if ( !user.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Collection<Interest> interests = user.get().getInterests();
        return interests.stream().map(interestMapper::interestToInterestDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findByIdPartEvents(Integer id) throws PrendPlaceException {
        Optional<User> user = userRepository.findById(id);
        if ( !user.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Interest not found");
        }
        Collection<Event> partEvents = user.get().getPartEvents();
        return partEvents.stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto create(UserDto userDto) {
        User event = userMapper.userDtoToUser(userDto);
        event = userRepository.save(event);
        return userMapper.userToUserDto(event);
    }

    @Override
    public UserDto addInterest(Integer id, Integer fk) throws PrendPlaceException {
        Optional<User> optionalUser = userRepository.findById(id);
        if ( !optionalUser.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        User user = optionalUser.get();

        Optional<Interest> optionalInterest = interestRepository.findById(fk);
        if ( !optionalInterest.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Interest not found");
        }
        Interest interest = optionalInterest.get();

        Collection<Interest> interests = user.getInterests();
        interests.add(interest);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto addEvent(Integer id, Integer fk) throws PrendPlaceException {
        Optional<User> optionalUser = userRepository.findById(id);
        if ( !optionalUser.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        User user = optionalUser.get();

        Optional<Event> optionalEvent = eventRepository.findById(fk);
        if ( !optionalEvent.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Event event = optionalEvent.get();

        Collection<Event> events = user.getEvents();
        events.add(event);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto addPartEvent(Integer id, Integer fk) throws PrendPlaceException {
        Optional<User> optionalUser = userRepository.findById(id);
        if ( !optionalUser.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        User user = optionalUser.get();

        Optional<Event> optionalEvent = eventRepository.findById(fk);
        if ( !optionalEvent.isPresent() ) {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
        Event partEvent = optionalEvent.get();

        Collection<Event> partEvents = user.getPartEvents();
        partEvents.add(partEvent);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    /*@Transactional
    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if ( !optional.isPresent() ) {
            throw new ResourceNotFoundException("No user found with id " + id);
        }
        User user = optional.get();
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserByIdInterests(Integer id, Integer fk) {
        Optional<User> optionalUser = userRepository.findById(id);
        if ( !optionalUser.isPresent() ) {
            throw new ResourceNotFoundException("No user found with id " + id);
        }
        User user = optionalUser.get();

        if ( user.getInterests() == null ) {
            user.setInterests(new HashSet<>());
        }

        Optional<Interest> optionalInterest = interestRepository.findById(id);
        if ( !optionalInterest.isPresent() ) {
            throw new ResourceNotFoundException("No interest found with id " + id);
        }
        Interest interest = optionalInterest.get();

        Collection<Interest> interests = user.getInterests();
        interests.add(interest);
        user.setInterests(interests);

        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }*/
}
