package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserFeedback;

import java.time.LocalDateTime;

public class UserFeedbackDto {

        private int feedbackId;
        private int orderId;
        private int fromUser;
        private int toUser;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;

        public UserFeedbackDto() {
        }

        public UserFeedbackDto(int feedbackId, int orderId, int fromUser, int toUser, int rating,
                            String comment, LocalDateTime createdAt) {
            this.feedbackId = feedbackId;
            this.orderId = orderId;
            this.fromUser = fromUser;
            this.toUser = toUser;
            this.rating = rating;
            this.comment = comment;
            this.createdAt = createdAt;
        }

        public UserFeedback toUserFeedBack(){
            UserFeedback uc = new UserFeedback( feedbackId, null, null,null,  rating, comment, createdAt);
            return uc;
        }

        public static UserFeedbackDto toDto(UserFeedback uc){
            return new UserFeedbackDto(uc.getFeedbackId(),uc.getOrder().getOrderId(), uc.getFromUser().getUserId(), uc.getToUser().getUserId(),
            uc.getRating(), uc.getComment(), uc.getCreatedAt());
        }

        public int getFeedbackId() {
            return feedbackId;
        }

        public void setFeedbackId(int feedbackId) {
            this.feedbackId = feedbackId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getFromuser() {
            return fromUser;
        }

        public void setFromuser(int fromuser) {
            fromUser = fromuser;
        }

        public int getToUser() {
            return toUser;
        }

        public void setToUser(int toUser) {
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
    }

