package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.UserComment;
import org.generation.italy.collectionarchive.models.entities.UserLike;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.UserProfileService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.UserCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/comments")
public class UserCommentRestController {

    private final UserProfileService userService;

    @Autowired
    public UserCommentRestController(UserProfileService userService) {
        this.userService = userService;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllComments(@RequestParam(required = false) Integer collectionId) {
        List<UserCommentDto> comments;
        try {
            if (collectionId != null) {
                comments = userService.findUserCommentByCollectionId(collectionId)
                        .stream()
                        .map(UserCommentDto::toDto)
                        .toList();
                return ResponseEntity.ok(comments);
            } else {
                comments = userService.findAllUserComments()
                        .stream()
                        .map(UserCommentDto::toDto)
                        .toList();
                return ResponseEntity.ok(comments);
            }
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable int id) {
        try {
            Optional<UserComment> comment = userService.findCommentById(id);
            if(comment.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            UserCommentDto dto = UserCommentDto.toDto(comment.get());
            return ResponseEntity.ok(dto);
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody UserCommentDto dto) {
        try {
            UserComment comment = dto.toUserComment();
            UserComment created = userService.createUserComment(comment, dto.getUserId(), dto.getItemId(), dto.getCollectionId(), dto.getComment());
            return ResponseEntity.status(HttpStatus.CREATED).body(UserCommentDto.toDto(created));
        } catch (DataException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable int id, @RequestBody UserCommentDto dto) {
        try {
            UserComment comment = dto.toUserComment(); // se hai un metodo per convertirlo
            comment.setCommentId(id);
            boolean updated = userService.updateUserComment(comment, dto.getUserId(), dto.getItemId());
            return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (DataException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        try {
            boolean deleted = userService.deleteUserComment(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}