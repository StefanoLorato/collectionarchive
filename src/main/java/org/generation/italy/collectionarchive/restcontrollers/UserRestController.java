package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.service.JpaUserService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

@GetMapping
public ResponseEntity<List<UserDto>> getAllUsers() {
    try {
        List<UserDto> users = UserService.findAllUsers()
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(users);
    } catch (DataException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
@GetMapping("/{id}")
public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
    try {
        Optional<User> user = UserService.findUserById(id);
        return user.map(u -> ResponseEntity.ok(toDto(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (DataException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    // POST
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        try {
            User created = userService.createUser(userMapper.fromDto(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(created));
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserDto dto) {
        try {
            User user = userMapper.fromDto(dto);
            user.setUserId(id);
            boolean updated = userService.updateUser(user);
            return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // CANCELLA L'UTENTE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            boolean deleted = userService.deleteUser(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}