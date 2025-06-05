package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserComment;

public class UserCommentDto {

    private int commentId;
    private int user;
    private int item;
    private String comment;

    public UserCommentDto() {
    }

    public UserCommentDto(int commentId, int user, int item, String comment) {
        this.commentId = commentId;
        this.user = user;
        this.item = item;
        this.comment = comment;
    }

    public UserComment toUserComment(){
        UserComment uc = new UserComment( commentId, null,null,comment);
        return uc;
    }

    public static UserCommentDto toDto(UserComment uc){
        return new UserCommentDto(uc.getCommentId(), uc.getUser().getUserId(), uc.getItem().getItemId(), uc.getComment());
    }



    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
