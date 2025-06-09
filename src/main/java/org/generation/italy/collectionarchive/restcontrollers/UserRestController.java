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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(JpaUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers().stream()
                .map(UserDto::toDto)
                .peek(dto -> dto.setPassword(null)) // fai sparire la password qui
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        try {
            Optional<User> user = userService.findUserById(id);
            if(user.isEmpty()) return ResponseEntity.notFound().build();

            UserDto dto = UserDto.toDto(user.get());
            dto.setPassword(null);  // Non mandare la password
            return ResponseEntity.ok(dto);
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            User user = userDto.toUser();
            User created = userService.createUser(user);
            UserDto responseDto = UserDto.toDto(created);
            responseDto.setPassword(null);  // sempre no password in output
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        try {
            User user = userDto.toUser();
            user.setUserId(id);
            boolean updated = userService.updateUser(user);
            return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

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
