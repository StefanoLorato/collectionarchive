package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.restdto.UserCommentDto;

@Entity
@Table (name = "user_comments")
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "comment_id")
    private int commentId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "object_id")
    private Item item;
    private String comment;

    public UserComment() {
    }

    public UserComment(int commentId, User user, Item item, String comment) {
        this.commentId = commentId;
        this.user = user;
        this.item = item;
        this.comment = comment;
    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
