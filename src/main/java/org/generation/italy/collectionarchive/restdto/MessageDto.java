package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.generation.italy.collectionarchive.models.entities.Message;
import org.generation.italy.collectionarchive.models.entities.User;

import java.time.LocalDateTime;

public class MessageDto {
    private Integer messageId;
    private Integer discussionId;
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private LocalDateTime sentAt;

    public MessageDto() {}

    public MessageDto(Integer messageId, Integer discussionId, Integer senderId, Integer receiverId, String content, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.discussionId = discussionId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.sentAt = sentAt;
    }

    public Integer getMessageId() { return messageId; }
    public void setMessageId(Integer messageId) { this.messageId = messageId; }

    public Integer getDiscussionId() { return discussionId; }
    public void setDiscussionId(Integer discussionId) { this.discussionId = discussionId; }

    public Integer getSenderId() { return senderId; }
    public void setSenderId(Integer senderId) { this.senderId = senderId; }

    public Integer getReceiverId() { return receiverId; }
    public void setReceiverId(Integer receiverId) { this.receiverId = receiverId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }

    public static MessageDto toDto(Message m) {
        return new MessageDto(
                m.getMessageId(),
                m.getDiscussion().getDiscussionId(),
                m.getSender().getUserId(),
                m.getReceiver().getUserId(),
                m.getContent(),
                m.getSentAt()
        );
    }

    public Message toMessage() {
        Message msg = new Message();
        msg.setMessageId(this.messageId);
        msg.setContent(this.content);
        msg.setSentAt(this.sentAt);
        return msg;
    }
}
