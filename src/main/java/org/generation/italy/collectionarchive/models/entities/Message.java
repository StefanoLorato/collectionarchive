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
    private int messageId;
    @ManyToOne
    @JoinColumn (name = "discussion_id")
    private Discussion discussion;
    private String content;
    @Column (name = "sent_at")
    private LocalDateTime sentAt;

    @OneToMany(mappedBy = "message")
    List<Notification> messageNotification = new ArrayList<>();
    @OneToMany(mappedBy = "message")
    private List<Report> reportedMessages = new ArrayList<>();

    public Message() {
    }

    public Message(int messageId, Discussion discussion, String content, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.discussion = discussion;
        this.content = content;
        this.sentAt = sentAt;
    }

    public int getMessageId() {
        return messageId;
    }
    public void setMessageId(int messageId) {
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
}
