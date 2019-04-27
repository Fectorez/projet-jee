package com.esgi.projetjee.service;

import com.esgi.projetjee.dao.InterestRepository;
import com.esgi.projetjee.model.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public boolean saveInterest(Interest interest) {
        interestRepository.save(interest);
        return true;
    }
}
