package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.entities.Message;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.DiscussionService;
import org.generation.italy.collectionarchive.models.service.MessageService;
import org.generation.italy.collectionarchive.restdto.DiscussionDto;
import org.generation.italy.collectionarchive.restdto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/api/discussions")
public class DiscussionRestController {
    private DiscussionService discussionService;
    private MessageService messageService;

    @Autowired
    public DiscussionRestController(DiscussionService discussionService, MessageService messageService) {
        this.discussionService = discussionService;
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<?> createDiscussion(@AuthenticationPrincipal User user, @RequestBody DiscussionDto dto) {
        List<MessageDto> msgDtos = dto.getMessages();
        Discussion discussion = dto.toDiscussion();
        discussionService.createDiscussion(discussion, dto.getBuyerId(), dto.getSellerId(), dto.getItemId(), dto.getCollectionId());
        msgDtos.forEach(msgDto -> {
            Message m = msgDto.toMessage();
            m.setSentAt(LocalDateTime.now());
            messageService.saveMessage(m, discussion.getDiscussionId(), msgDto.getSenderId(), msgDto.getReceiverId());
            discussion.addMessage(m);
        });
        Discussion created = discussionService.findDiscussionById(discussion.getDiscussionId()).orElseThrow(() -> new EntityNotFoundException("Errore nel caricamento della discussione con id: " + discussion.getDiscussionId()));
        DiscussionDto saved = DiscussionDto.toDto(created, true);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getDiscussionId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> getAllDiscussions(@RequestParam(required = false) Integer collectionId, @AuthenticationPrincipal User user) {
        try {
            if(collectionId != null && user != null) {
                List<DiscussionDto> dtos = discussionService.getDiscussionByCollectionIdAndUserId(collectionId, user.getUserId())
                        .stream().map(d -> DiscussionDto.toDto(d, true)).toList();
                return ResponseEntity.ok(dtos);
            }
            List<Discussion> discussions = discussionService.getAllDiscussions();
            List<DiscussionDto> dtos = discussions.stream()
                    .map(d -> DiscussionDto.toDto(d, true))
                    .toList();
            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getDiscussionByUserId(@PathVariable("id") Integer id) throws DataException {
        List<DiscussionDto> dtos = discussionService.getDiscussionsByUserId(id)
                .stream().map(d -> DiscussionDto.toDto(d, true)).toList();
        return ResponseEntity.ok(dtos);


    }

}