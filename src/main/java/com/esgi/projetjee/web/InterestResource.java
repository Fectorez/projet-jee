package com.esgi.projetjee.web;

import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.InterestService;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.dto.UserDto;
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

    @GetMapping("/{id}/events")
    public List<EventDto> findByIdEvents(@PathVariable Integer id) throws PrendPlaceException {
        return interestService.findByIdEvents(id);
    }

    @GetMapping("/{id}/users")
    public List<UserDto> findByIdUsers(@PathVariable Integer id) throws PrendPlaceException {
        return interestService.findByIdUsers(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        interestService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterestDto create(@RequestBody InterestDto interestDto) {
        return interestService.create(interestDto);
    }

    @PutMapping("/{id}/events/{fk}")
    public InterestDto addEvent(@PathVariable Integer id, @PathVariable Integer fk) throws PrendPlaceException {
        return interestService.addEvent(id, fk);
    }
}
