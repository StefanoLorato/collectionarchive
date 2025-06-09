package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.service.JpaUserService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.OrderDto;
import org.generation.italy.collectionarchive.restdto.UserDto;
import org.generation.italy.collectionarchive.restdto.logindto.UserInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;
    //private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRestController(JpaUserService userService) { //Inserire il passwordEndcore in input
        this.userService = userService;
        //this.passwordEncoder = passwordEncoder;
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
            dto.setPassword(null);
            return ResponseEntity.ok(dto);
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserInputDto inputDto) {
        try {
            User user = inputDto.toEntity();
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            User created = userService.createUser(user);
            UserDto dto = UserDto.toDto(created);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore nella creazione dell'utente: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username o e-mail già in uso! " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserInputDto inputDto) {
        try {
            User user = inputDto.toEntity();
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
