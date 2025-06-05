package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.UserComment;

public class UserCommentDto {

    private int commentId;
    private int userId;
    private int itemId;
    private String comment;

    public UserCommentDto() {
    }

    public UserCommentDto(int commentId, int userId, int itemId, String comment) {
        this.commentId = commentId;
        this.userId = userId;
        this.itemId = itemId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
