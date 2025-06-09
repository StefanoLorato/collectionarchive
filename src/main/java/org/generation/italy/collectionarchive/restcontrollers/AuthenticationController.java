package org.generation.italy.collectionarchive.restcontrollers;

import jakarta.validation.Valid;
import org.generation.italy.collectionarchive.models.service.AuthenticationService;
import org.generation.italy.collectionarchive.restdto.AuthenticationRequestDto;
import org.generation.italy.collectionarchive.restdto.AuthenticationResponse;
import org.generation.italy.collectionarchive.restdto.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequestDto request) throws Exception {
        authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequestDto authRequest) {
        return authenticationService.login(authRequest);
    }

    }

