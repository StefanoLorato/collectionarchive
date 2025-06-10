package org.generation.italy.collectionarchive.restcontrollers;

import jakarta.validation.Valid;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.service.AuthenticationService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private AuthenticationService authenticationService;
    private UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) throws Exception {
        authenticationService.register(registerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto authRequestDto){
        return ResponseEntity.ok(authenticationService.login(authRequestDto));
    }

}
