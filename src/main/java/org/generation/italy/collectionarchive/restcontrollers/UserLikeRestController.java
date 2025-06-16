package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.UserLike;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.JpaUserService;
import org.generation.italy.collectionarchive.models.service.UserProfileService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.UserLikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/userlikes")
public class UserLikeRestController {

    private final UserProfileService userService;

    @Autowired
    public UserLikeRestController(UserProfileService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUserLikes() {
        try {
            List<UserLikeDto> likes = userService.findAllUserLikes()
                    .stream()
                    .map(UserLikeDto::toDto)
                    .toList();
            return ResponseEntity.ok(likes);
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserLikeById(@PathVariable int id) {
        try {
            Optional<UserLike> ul = userService.findUserLikeById(id);
            return ul.map(l -> ResponseEntity.ok(UserLikeDto.toDto(l)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserLikesByUserId(@PathVariable int userId) {
        try {
            List<UserLikeDto> likes = userService.findUserLikesByUserId(userId)
                    .stream()
                    .map(UserLikeDto::toDto)
                    .toList();
            return ResponseEntity.ok(likes);
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUserLike(@RequestBody UserLikeDto dto) {
        try {
            boolean alreadyLiked = userService.userAlreadyLikedItem(dto.getUser(), dto.getItemId());
            if (alreadyLiked) {
                return ResponseEntity.badRequest().body("Utente ha già messo like a questo item");
            }

            UserLike created = userService.createUserLike(dto.getUser(), dto.getItemId());
            UserLikeDto savedDto = UserLikeDto.toDto(created);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedDto.getLikeId())
                    .toUri();

            return ResponseEntity.created(location).body(savedDto);

        } catch (DataException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserLike(@PathVariable int id) {
        try {
            boolean deleted = userService.deleteUserLike(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}