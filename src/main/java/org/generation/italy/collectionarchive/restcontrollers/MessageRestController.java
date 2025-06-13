package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.service.MessageService;
import org.generation.italy.collectionarchive.restdto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussions")
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{discussionId}/messages")
    public ResponseEntity<List<MessageDto>> getMessagesByDiscussion(@PathVariable Integer discussionId) {
        List<MessageDto> messages = messageService.getMessagesByDiscussionId(discussionId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/{discussionId}/messages")
    public ResponseEntity<MessageDto> createMessage(
            @PathVariable Integer discussionId,
            @RequestBody MessageDto dto
    ) {
        dto.setDiscussionId(discussionId); // Assicurati che venga settato
        MessageDto saved = messageService.saveMessage(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}


