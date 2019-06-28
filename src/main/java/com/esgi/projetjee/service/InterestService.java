package com.esgi.projetjee.service;

import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InterestService {
    List<InterestDto> findAll();
    Optional<InterestDto> findOne(Integer id);
    void delete(Integer id);
    InterestDto create(InterestDto interestDto);
    List<EventDto> findByIdEvents(Integer id) throws PrendPlaceException;
    List<UserDto> findByIdUsers(Integer id) throws PrendPlaceException;
    InterestDto addEvent(Integer id, Integer fk) throws PrendPlaceException;
}
