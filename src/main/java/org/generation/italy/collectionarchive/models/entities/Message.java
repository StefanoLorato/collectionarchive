package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;
    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussion;
    private String content;
    @Column (name = "sent_at")
    private LocalDateTime sentAt;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;



    @OneToMany(mappedBy = "message")
    List<Notification> messageNotification = new ArrayList<>();
    @OneToMany(mappedBy = "message")
    private List<Report> reportedMessages = new ArrayList<>();


    public Message() {
    }

    public Message(Integer messageId, Discussion discussion, String content, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.discussion = discussion;
        this.content = content;
        this.sentAt = sentAt;
    }

    public Integer getMessageId() {
        return messageId;
    }
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Discussion getDiscussion() {
        return discussion;
    }
    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
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

    public List<Notification> getMessageNotification() {
        return messageNotification;
    }

    public void setMessageNotification(List<Notification> messageNotification) {
        this.messageNotification = messageNotification;
    }

    public List<Report> getReportedMessages() {
        return reportedMessages;
    }

    public void setReportedMessages(List<Report> reportedMessages) {
        this.reportedMessages = reportedMessages;
    }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }

    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }
}