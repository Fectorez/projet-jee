package com.esgi.projetjee.service;

import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    List<UserDto> findAll();
    Optional<UserDto> findOne(Integer id);
    List<EventDto> findByIdEvents(Integer id) throws PrendPlaceException;
    List<InterestDto> findByIdInterests(Integer id) throws PrendPlaceException;
    List<EventDto> findByIdPartEvents(Integer id) throws PrendPlaceException;
    void delete(Integer id);
    UserDto create(UserDto userDto);
    UserDto addInterest(Integer id, Integer fk) throws PrendPlaceException;
    UserDto addEvent(Integer id, Integer fk) throws PrendPlaceException;
    UserDto addPartEvent(Integer id, Integer fk) throws PrendPlaceException;
}
