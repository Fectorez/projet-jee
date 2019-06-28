package com.esgi.projetjee.service;

import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDto> findAll();
    Optional<EventDto> findOne(Integer id);
    void delete(Integer id);
    EventDto create(EventDto eventDto);
    List<InterestDto> findByIdInterests(Integer id) throws PrendPlaceException;
    EventDto addInterest(Integer id, Integer fk) throws PrendPlaceException;
}
