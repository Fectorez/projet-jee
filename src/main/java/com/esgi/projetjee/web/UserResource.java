package com.esgi.projetjee.web;

import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.exception.PrendPlaceException;
import com.esgi.projetjee.service.UserService;
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
            throw new PrendPlaceException(HttpStatus.NOT_FOUND.value(), "Event not found");
        }
    }

    /*@GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createOrUpdateUser(user);
    }

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
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }*/
}
