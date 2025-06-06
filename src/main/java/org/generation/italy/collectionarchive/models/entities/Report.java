package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;
    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    private User reportedUser;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private UserComment comment;
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;
    private String content;
    private String status;
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    public Report() {
    }

    public Report(int reportId, User reporter, User reportedUser, Item item, Collection collection, UserComment comment,
                  Message message, String content, String status, LocalDateTime createdAt) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.reportedUser = reportedUser;
        this.item = item;
        this.collection = collection;
        this.comment = comment;
        this.message = message;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getReportId() {
        return reportId;
    }
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public User getReporter() {
        return reporter;
    }
    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getReportedUser() {
        return reportedUser;
    }
    public void setReportedUser(User reportedUser) {
        this.reportedUser = reportedUser;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public Collection getCollection() {
        return collection;
    }
    public void setCollection(Collection collection) {
        this.collection = collection;
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

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
