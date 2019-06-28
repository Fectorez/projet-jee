package com.esgi.projetjee.service;

import com.esgi.projetjee.service.dto.InterestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InterestService {
    List<InterestDto> findAll();
    Optional<InterestDto> findOne(Integer id);
}
