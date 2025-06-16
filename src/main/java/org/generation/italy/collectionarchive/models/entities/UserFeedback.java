package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_feedbacks")
public class UserFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "feedback_id")
    private int feedbackId;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn (name = "from_user_id")
    private User fromUser;
    @ManyToOne
    @JoinColumn (name = "to_user_id")
    private User toUser;
    private int rating;
    private String comment;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "feedback")
    List<Notification> feedbackNotification = new ArrayList<>();

    public UserFeedback() {
    }

    public UserFeedback(int feedbackId, Order order, User fromUser, User toUser, int rating,
                        String comment, LocalDateTime createdAt) {
        this.feedbackId = feedbackId;
        this.order = order;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Notification> getFeedbackNotification() {
        return feedbackNotification;
    }

    public void setFeedbackNotification(List<Notification> feedbackNotification) {
        this.feedbackNotification = feedbackNotification;
    }
}
