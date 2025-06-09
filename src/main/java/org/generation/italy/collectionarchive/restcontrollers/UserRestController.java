package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.service.JpaUserService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.OrderDto;
import org.generation.italy.collectionarchive.restdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // GET tutti gli utenti
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers()
                .stream()
                .map(UserDto::toDto)
                .peek(dto -> dto.setPassword(null)) // nascondi la password
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // GET utente per ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        Optional<User> userOpt = userService.findUserById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserDto dto = UserDto.toDto(userOpt.get());
        dto.setPassword(null); // occhio alla password
        return ResponseEntity.ok(dto);
    }

    // POST nuovo utente
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        // Qui in futuro encoder per la password
        User created = userService.createUser(user);
        UserDto createdDto = UserDto.toDto(created);
        createdDto.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    // PUT per aggiornare utente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        User user = userDto.toUser();
        user.setUserId(id);
        boolean success = userService.updateUser(user);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // DELETE utente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean success = userService.deleteUser(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

