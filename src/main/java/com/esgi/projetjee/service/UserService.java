package com.esgi.projetjee.service;

import com.esgi.projetjee.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    List<UserDto> findAll();
    Optional<UserDto> findOne(Integer id);
}
