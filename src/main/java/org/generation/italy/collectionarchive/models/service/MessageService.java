package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.entities.Message;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DiscussionNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.DiscussionRepository;
import org.generation.italy.collectionarchive.models.repositories.MessageRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageDto saveMessage(MessageDto dto) {
        if (dto.getDiscussionId() == null) throw new IllegalArgumentException("DiscussionId non può essere null");
        if (dto.getSenderId() == null) throw new IllegalArgumentException("SenderId non può essere null");
        if (dto.getReceiverId() == null) throw new IllegalArgumentException("ReceiverId non può essere null");

        Discussion discussion = discussionRepository.findById(dto.getDiscussionId())
                .orElseThrow(() -> new DiscussionNotFoundException("Discussione non trovata!"));

        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));


        if (!((discussion.getBuyer().equals(sender) && discussion.getSeller().equals(receiver)) ||
                (discussion.getSeller().equals(sender) && discussion.getBuyer().equals(receiver)))) {
            throw new IllegalArgumentException("Sender and receiver must be buyer and seller of the discussion");
        }

        Message msg = new Message();
        msg.setDiscussion(discussion);
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setContent(dto.getContent());
        msg.setSentAt(LocalDateTime.now());

        Message saved = messageRepository.save(msg);

        return toDto(saved);
    }

    private MessageDto toDto(Message m) {
        MessageDto dto = new MessageDto();
        dto.setMessageId(m.getMessageId());
        dto.setDiscussionId(m.getDiscussion().getDiscussionId());
        dto.setSenderId(m.getSender().getUserId());
        dto.setReceiverId(m.getReceiver().getUserId());
        dto.setContent(m.getContent());
        dto.setSentAt(m.getSentAt());
        return dto;
    }
    public List<MessageDto> getMessagesByDiscussionId(Integer discussionId) {
        List<Message> messages = messageRepository.findByDiscussion_DiscussionIdOrderBySentAtAsc(discussionId);
        return messages.stream()
                .map(this::toDto)
                .toList();
    }

}



