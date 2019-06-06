package com.esgi.projetjee.service;

import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.domain.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class InterestService {

    private final InterestRepository interestRepository;

    @Autowired
    public InterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    @Transactional(readOnly = true)
    public List<Interest> getInterests() {
        return interestRepository.findAll();
    }

    @Transactional(readOnly = true)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Interest getInterest(Integer id) {
        Optional<Interest> optional = interestRepository.findById(id);
        if ( !optional.isPresent() ) {
            throw new ResourceNotFoundException("No interest found with id " + id);
        }
        return optional.get();
    }

    @Transactional
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
    }
}
