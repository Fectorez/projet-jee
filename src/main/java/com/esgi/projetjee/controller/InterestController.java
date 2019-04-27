package com.esgi.projetjee.controller;

import com.esgi.projetjee.model.Interest;
import com.esgi.projetjee.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/interests")
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public List<Interest> getInterests() {
        return interestService.getInterests();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Interest createInterest(@RequestBody Interest interest) {
        interestService.saveInterest(interest);
        return interest;
    }
}
