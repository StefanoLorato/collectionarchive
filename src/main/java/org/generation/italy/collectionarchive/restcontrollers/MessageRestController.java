package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Message;
import org.generation.italy.collectionarchive.models.service.MessageService;
import org.generation.italy.collectionarchive.restdto.ItemDto;
import org.generation.italy.collectionarchive.restdto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {
    private MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/discussion/{discussionId}")
    public ResponseEntity<List<MessageDto>> getMessagesByDiscussion(@PathVariable Integer discussionId) {
        List<MessageDto> messages = messageService.getMessagesByDiscussionId(discussionId).stream().map(MessageDto::toDto).toList();;
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto dto) {
        Message msg = dto.toMessage();
        messageService.saveMessage(msg, dto.getDiscussionId(), dto.getSenderId(), dto.getReceiverId());
        MessageDto saved = MessageDto.toDto(msg);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}