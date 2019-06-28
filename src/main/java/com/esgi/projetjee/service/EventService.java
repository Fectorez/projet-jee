package com.esgi.projetjee.service;

import com.esgi.projetjee.service.dto.EventDto;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDto> findAll();
    Optional<EventDto> findOne(Integer id);
}
