package com.esgi.projetjee.service.impl;

import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.exception.ResourceNotFoundException;
import com.esgi.projetjee.repository.UserRepository;
import com.esgi.projetjee.service.UserService;
import com.esgi.projetjee.service.dto.UserDto;
import com.esgi.projetjee.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if ( user == null ) {
            throw new UsernameNotFoundException("No user present with username : " + username);
        }
        else {
            return user;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Optional<UserDto> findOne(Integer id) {
        return userRepository.findById(id).map(userMapper::userToUserDto);
    }

    /*@Transactional
    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if ( !optional.isPresent() ) {
            throw new ResourceNotFoundException("No user found with id " + id);
        }
        User user = optional.get();
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserByIdInterests(Integer id, Integer fk) {
        Optional<User> optionalUser = userRepository.findById(id);
        if ( !optionalUser.isPresent() ) {
            throw new ResourceNotFoundException("No user found with id " + id);
        }
        User user = optionalUser.get();

        if ( user.getInterests() == null ) {
            user.setInterests(new HashSet<>());
        }

        Optional<Interest> optionalInterest = interestRepository.findById(id);
        if ( !optionalInterest.isPresent() ) {
            throw new ResourceNotFoundException("No interest found with id " + id);
        }
        Interest interest = optionalInterest.get();

        Collection<Interest> interests = user.getInterests();
        interests.add(interest);
        user.setInterests(interests);

        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }*/
}
