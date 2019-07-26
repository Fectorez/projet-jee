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
@RequestMapping("/health")
public class HealthResource {

    @Autowired
    public HealthResource() {

    }

    @GetMapping
    public String health() {
        return "health ok";
    }
}
