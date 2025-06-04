package org.generation.italy.collectionarchive.restdto;


import org.generation.italy.collectionarchive.models.entities.*;

import java.time.LocalDateTime;

public class ReportDto {
    private int reportId;
    private int reporter;
    private int reportedUser;
    private int item;
    private int collection;
    private int comment;
    private int message;
    private String content;
    private String status;
    private LocalDateTime createdAt;

    public ReportDto() {
    }

    public ReportDto(int reportId, int reporter, int reportedUser, int item, int collection, int comment, int message,
                     String content, String status, LocalDateTime createdAt) {
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

    public Report toReport(){
        return new Report(reportId, null, null, null, null, null, null, content, status, createdAt);
    }

    public static ReportDto toDto(Report r){
        return new ReportDto(r.getReportId(), r.getReporter().getUserId(), r.getReportedUser().getUserId(),
                r.getItem().getItemId(), r.getCollection().getCollectionId(), r.getComment().getCommentId(),
                r.getMessage().getMessageId(), r.getContent(), r.getStatus(), r.getCreatedAt());
    }

    public int getReportId() {
        return reportId;
    }
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getReporter() {
        return reporter;
    }
    public void setReporter(int reporter) {
        this.reporter = reporter;
    }

    public int getReportedUser() {
        return reportedUser;
    }
    public void setReportedUser(int reportedUser) {
        this.reportedUser = reportedUser;
    }

    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }

    public int getCollection() {
        return collection;
    }
    public void setCollection(int collection) {
        this.collection = collection;
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
