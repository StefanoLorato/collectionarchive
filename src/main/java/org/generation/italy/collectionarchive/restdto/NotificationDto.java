package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Notification;

import java.time.LocalDateTime;

public class NotificationDto {
    private int notificationId;
    private int user;
    private int fromUser;
    private int like;
    private int comment;
    private int message;
    private int feedback;
    private String content;
    private boolean read;
    private LocalDateTime createdAt;

    public NotificationDto() {
    }

    public NotificationDto(int notificationId, int user, int fromUser, int like, int comment, int message, int feedback,
                           String content, boolean read, LocalDateTime createdAt) {
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

    public Notification toNotification(){
        return new Notification(notificationId, null, null, null, null, null, null, content, read, createdAt);
    }

    public static NotificationDto toDto(Notification n){
        return new NotificationDto(n.getNotificationId(), n.getUser().getUserId(), n.getFromUser().getUserId(),
                                    n.getLike().getLikeId(), n.getComment().getCommentId(), n.getMessage().getMessageId(),
                                    n.getFeedback().getFeedbackId(), n.getContent(), n.isRead(), n.getCreatedAt())
    }

    public int getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUser() {
        return user;
    }
    public void setUser(int user) {
        this.user = user;
    }

    public int getFromUser() {
        return fromUser;
    }
    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public int getLike() {
        return like;
    }
    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }
    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getMessage() {
        return message;
    }
    public void setMessage(int message) {
        this.message = message;
    }

    public int getFeedback() {
        return feedback;
    }
    public void setFeedback(int feedback) {
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
