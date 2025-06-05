package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Message;

import java.time.LocalDateTime;

public class MessageDto {
    private int messageId;
    private int discussionId;
    private String content;
    private LocalDateTime sentAt;

    public MessageDto() {
    }

    public MessageDto(int messageId, int discussionId, String content, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.discussionId = discussionId;
        this.content = content;
        this.sentAt = sentAt;
    }

    public Message toMessage(){
        return new Message(messageId, null, content, sentAt);
    }

    public static MessageDto toDto(Message m){
        return new MessageDto(m.getMessageId(), m.getDiscussion().getDiscussionId(), m.getContent(), m.getSentAt());
    }


    public int getMessageId() {
        return messageId;
    }
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getDiscussion() {
        return discussionId;
    }
    public void setDiscussion(int discussionId) {
        this.discussionId = discussionId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
