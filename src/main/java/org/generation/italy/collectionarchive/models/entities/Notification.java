package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "notification_id")
    private int notificationId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "like_id")
    private UserLike like;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private UserComment comment;
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;
    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private UserFeedback feedback;
    private String content;
    private boolean read;
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    public Notification() {
    }

    public Notification(int notificationId, User user, User fromUser, UserLike like, UserComment comment,
                        Message message, UserFeedback feedback, String content, boolean read, LocalDateTime createdAt) {
        this.notificationId = notificationId;
        this.user = user;
        this.fromUser = fromUser;
        this.like = like;
        this.comment = comment;
        this.message = message;
        this.feedback = feedback;
        this.content = content;
        this.read = read;
        this.createdAt = createdAt;
    }

    public int getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public User getFromUser() {
        return fromUser;
    }
    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public UserLike getLike() {
        return like;
    }
    public void setLike(UserLike like) {
        this.like = like;
    }

    public UserComment getComment() {
        return comment;
    }
    public void setComment(UserComment comment) {
        this.comment = comment;
    }

    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }

    public UserFeedback getFeedback() {
        return feedback;
    }
    public void setFeedback(UserFeedback feedback) {
        this.feedback = feedback;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return read;
    }
    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
