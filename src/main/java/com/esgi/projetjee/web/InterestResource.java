package com.esgi.projetjee.web;

import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.InterestService;
import com.esgi.projetjee.service.dto.InterestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interests")
public class InterestResource {

    private final InterestService interestService;

    @Autowired
    public InterestResource(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public List<InterestDto> findAll() {
        return interestService.findAll();
    }

    @GetMapping("/{id}")
    public InterestDto findOne(@PathVariable Integer id) throws PrendPlaceException {
        Optional<InterestDto> interest = interestService.findOne(id);
        if ( interest.isPresent() ) {
            return interest.get();
        }
        else {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Interest not found");
        }
    }

    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Interest createInterest(@RequestBody Interest interest) {
        return interestService.createOrUpdateInterest(interest);
    }

    @PutMapping
    public Interest updateInterests(@RequestBody Interest interest) {
        return interestService.createOrUpdateInterest(interest);
    }

    @PutMapping("/{id}")
    public Interest updateInterest(@PathVariable Integer id) {
        return interestService.updateInterestById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteInterest(@PathVariable Integer id) {
        interestService.deleteInterestById(id);
    }*/
}
