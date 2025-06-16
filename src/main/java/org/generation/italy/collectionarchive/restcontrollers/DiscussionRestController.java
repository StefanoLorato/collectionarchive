package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.service.DiscussionService;
import org.generation.italy.collectionarchive.restdto.DiscussionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionRestController {
    private DiscussionService discussionService;

    @Autowired
    public DiscussionRestController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @PostMapping
    public ResponseEntity<?> createDiscussion(@RequestBody DiscussionDto dto) {
        Discussion discussion = dto.toDiscussion();
        Discussion created = discussionService.createDiscussion(discussion, dto.getBuyerId(), dto.getSellerId(), dto.getItemId(), dto.getCollectionId());
        DiscussionDto saved = DiscussionDto.toDto(created);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getDiscussionId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }
}