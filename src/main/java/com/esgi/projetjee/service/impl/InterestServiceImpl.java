package com.esgi.projetjee.service.impl;

import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.service.InterestService;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.mapper.InterestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("InterestService")
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final InterestMapper interestMapper;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository, InterestMapper interestMapper) {
        this.interestRepository = interestRepository;
        this.interestMapper = interestMapper;
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
