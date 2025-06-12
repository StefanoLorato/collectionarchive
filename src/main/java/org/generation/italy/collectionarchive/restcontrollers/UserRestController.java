package org.generation.italy.collectionarchive.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.service.UserProfileService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.PasswordUpdateRequestDto;
import org.generation.italy.collectionarchive.restdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserDto> getUserInfo() throws JsonProcessingException {
        UserDto dto = UserDto.toDto(userService.getUserInfo());
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/password")
    public ResponseEntity<Void> passwordUpdate(@Valid @RequestBody PasswordUpdateRequestDto passwordUpdateRequestDto){
        userService.updatePassword(passwordUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) throws JsonProcessingException {
        Optional<User> user = userService.findUserByEmail(email);
        UserDto dto = UserDto.toDto(user.get());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDto dto) {
        if(id != dto.getUserId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }
        Optional<User> ou = userService.findUserById(id);
        if(ou.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User u = dto.toUser();

        boolean updated = userService.updateUser(u);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        Optional<User> u = userService.findUserById(id);
        if(u.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        UserDto dto = UserDto.toDto(u.get());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateUser(@PathVariable int id) {
        Optional<User> ou = userService.findUserById(id);
        if(ou.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        boolean updated = userService.deactivateUser(id);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}