package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.entities.Message;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.exceptions.ReceiverNotFoundException;
import org.generation.italy.collectionarchive.models.exceptions.SenderNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.DiscussionRepository;
import org.generation.italy.collectionarchive.models.repositories.MessageRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JpaMessageService implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Message saveMessage(Message msg, Integer discussionId, Integer senderId, Integer receiverId) throws DataException {
        if (discussionId == null) throw new IllegalArgumentException("Devi inserire il numero della discussione!");
        if (senderId == null) throw new IllegalArgumentException("Inserisci il mittente!");
        if (receiverId == null) throw new IllegalArgumentException("Inserisci il ricevente!");

        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new EntityNotFoundException("Discussione non trovata!"));

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new SenderNotFoundException("Mittente non trovato!"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new ReceiverNotFoundException("Ricevente non trovato!"));

        System.out.println("buyer:" + discussion.getBuyer().getUserId() + "seller" + discussion.getSeller().getUserId());
        System.out.println("sender:" + senderId + "receiver" + receiverId);
        if (!((discussion.getBuyer().equals(sender) && discussion.getSeller().equals(receiver)) ||
                (discussion.getSeller().equals(sender) && discussion.getBuyer().equals(receiver)))) {
            throw new IllegalArgumentException("Mittente e ricevente non corrispondono alla discussione!");
        }

        msg.setDiscussion(discussion);
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setContent(msg.getContent());
        msg.setSentAt(LocalDateTime.now());

        Message saved = messageRepository.save(msg);

        return saved;
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

    @Override
    public List<Message> getMessagesByDiscussionId(Integer discussionId) throws DataException {
        return messageRepository.findByDiscussion_DiscussionIdOrderBySentAtAsc(discussionId);
    }

}