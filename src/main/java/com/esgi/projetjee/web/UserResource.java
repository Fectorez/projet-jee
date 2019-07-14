package com.esgi.projetjee.web;

import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.UserService;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findOne(@PathVariable Integer id) throws PrendPlaceException {
        Optional<UserDto> user = userService.findOne(id);
        if ( user.isPresent() ) {
            return user.get();
        }
        else {
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "User not found");
        }
    }

    @GetMapping("/{id}/interests")
    public List<InterestDto> findInterests(@PathVariable Integer id) throws PrendPlaceException {
        return userService.findByIdInterests(id);
    }

    @GetMapping("/{id}/events")
    public List<EventDto> findByIdEvents(@PathVariable Integer id) throws PrendPlaceException {
        return userService.findByIdEvents(id);
    }

    @GetMapping("/{id}/partEvents")
    public List<EventDto> findByIdPartEvents(@PathVariable Integer id) throws PrendPlaceException {
        return userService.findByIdPartEvents(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/{id}/interests/{fk}")
    public UserDto addInterest(@PathVariable Integer id, @PathVariable Integer fk) throws PrendPlaceException {
        return userService.addInterest(id, fk);
    }

    @PutMapping("/{id}/events/{fk}")
    public UserDto addEvent(@PathVariable Integer id, @PathVariable Integer fk) throws PrendPlaceException {
        return userService.addEvent(id, fk);
    }

    @PutMapping("/{id}/partEvents/{fk}")
    public UserDto addPartEvent(@PathVariable Integer id, @PathVariable Integer fk) throws PrendPlaceException {
        return userService.addPartEvent(id, fk);
    }
/*
    @PutMapping
    public User updateUsers(@RequestBody User user) {
        return userService.createOrUpdateUser(user);
    }

    @PutMapping("{id}/interests/{fk}")
    public User updateByIdInterests(@PathVariable Integer id, @PathVariable Integer fk) {
        return userService.updateUserByIdInterests(id, fk);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id) {
        return userService.updateUserById(id);
    }*/
}
