package org.generation.italy.collectionarchive.models.service;

import jakarta.validation.Valid;
import org.generation.italy.collectionarchive.security.JwtUtils;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.AuthenticationRequestDto;
import org.generation.italy.collectionarchive.restdto.AuthenticationResponse;
import org.generation.italy.collectionarchive.restdto.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// service/AuthenticationService.java
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public void register(@Valid RegisterRequestDto request) throws Exception {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new Exception("Username già usato");
        if (userRepository.existsByEmail(request.getEmail()))
            throw new Exception("Email già usata");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        user.setActive(true);

        userRepository.save(user);
    }

    public AuthenticationResponse login(@Valid AuthenticationRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new AuthenticationResponse(jwt);
    }
}
