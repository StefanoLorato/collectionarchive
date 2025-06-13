package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.service.DiscussionService;
import org.generation.italy.collectionarchive.restdto.DiscussionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionRestController {

    @Autowired
    private DiscussionService discussionService;

    @PostMapping
    public ResponseEntity<DiscussionDto> createDiscussion(@RequestBody DiscussionDto dto) {
        DiscussionDto created = discussionService.createDiscussion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
