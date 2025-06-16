package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.UserFeedback;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.UserProfileService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.UserFeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/userfeedbacks")
public class UserFeedbackRestController {

    private final UserProfileService userService;

    @Autowired
    public UserFeedbackRestController(UserProfileService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllFeedbacks() throws DataException {
        List<UserFeedbackDto> dtos = userService.findAllUserFeedbacks()
                .stream()
                .map(UserFeedbackDto::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable int id) throws DataException {
        Optional<UserFeedback> opt = userService.findUserFeedbackById(id);
        return opt.map(f -> ResponseEntity.ok(UserFeedbackDto.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getFeedbackByOrderId(@PathVariable int orderId) throws DataException {
        Optional<UserFeedback> opt = userService.findFeedbackByOrderId(orderId);
        return opt.map(f -> ResponseEntity.ok(UserFeedbackDto.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserFeedbackDto> createFeedback(@RequestBody UserFeedbackDto dto) throws DataException, EntityNotFoundException {
        UserFeedback feedback = userService.createUserFeedback(
                dto.getOrderId(),
                dto.getFromuser(),
                dto.getToUser(),
                dto.getRating(),
                dto.getComment()
        );

        UserFeedbackDto savedDto = UserFeedbackDto.toDto(feedback);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDto.getFeedbackId())
                .toUri();

        return ResponseEntity.created(location).body(savedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int id) throws DataException {
        boolean deleted = userService.deleteUserFeedback(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}