package com.esgi.projetjee.web;

import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interests")
public class InterestResource {

    private final InterestService interestService;

    @Autowired
    public InterestResource(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public List<Interest> getInterests() {
        return interestService.getInterests();
    }

    @GetMapping("/{id}")
    public Interest getInterest(@PathVariable Integer id){
        return interestService.getInterest(id);
    }

    @PostMapping
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
    }
}
