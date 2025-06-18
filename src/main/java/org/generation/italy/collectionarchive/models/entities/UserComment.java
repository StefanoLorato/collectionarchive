package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.restdto.UserCommentDto;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;
    private String comment;

    @OneToMany(mappedBy = "comment")
    List<Notification> commentNotification = new ArrayList<>();
    @OneToMany(mappedBy = "comment")
    private List<Report> reportedComments = new ArrayList<>();


    public UserComment() {
    }

    public UserComment(int commentId, User user, Item item, Collection collection, String comment) {
        this.commentId = commentId;
        this.user = user;
        this.item = item;
        this.collection = collection;
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

    public List<Notification> getCommentNotification() {
        return commentNotification;
    }

    public void setCommentNotification(List<Notification> commentNotification) {
        this.commentNotification = commentNotification;
    }

    public List<Report> getReportedComments() {
        return reportedComments;
    }

    public void setReportedComments(List<Report> reportedComments) {
        this.reportedComments = reportedComments;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
