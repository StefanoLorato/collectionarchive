package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Authority;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.AuthenticationRequestDto;
import org.generation.italy.collectionarchive.restdto.AuthenticationResponseDto;
import org.generation.italy.collectionarchive.restdto.RegisterRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JpaAuthenticationService implements AuthenticationService {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public JpaAuthenticationService(UserRepository userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void register(RegisterRequestDto input) throws Exception {
        if (isEmailTaken(input.getEmail())) {
            throw new Exception("Email already taken");
        }
        User user = buildNewUser(input);
        userRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponseDto login(AuthenticationRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(new HashMap<>(), user);

        return new AuthenticationResponseDto(jwtToken);
    }

    private boolean isEmailTaken(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    private User buildNewUser(RegisterRequestDto input) {
        User user = new User();
        user.setUserId(0);
        user.setName(input.getName());
        user.setLastname(input.getLastname());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCountry(input.getCountry());
        user.setActive(true);
        user.setAuthorities(initialAuthority());
        return user;
    }

    private List<Authority> initialAuthority() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_ADMIN"));
        authorities.add(new Authority("ROLE_CLIENT"));
        return authorities;
    }

}
