package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.UserComment;

public class UserCommentDto {

    private int commentId;
    private int userId;
    private Integer itemId;
    private Integer collectionId;
    private String comment;

    public UserCommentDto() {
    }

    public UserCommentDto(int commentId, int userId, Integer itemId, Integer collectionId, String comment) {
        this.commentId = commentId;
        this.userId = userId;
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.comment = comment;
    }

    public UserComment toUserComment(){
        UserComment uc = new UserComment( commentId, null,null, null,comment);
        return uc;
    }

    public static UserCommentDto toDto(UserComment uc){
        UserCommentDto dto = new UserCommentDto();

        dto.setCommentId(uc.getCommentId());
        dto.setUserId(uc.getUser().getUserId());

        if(uc.getItem() != null){
            dto.setItemId(uc.getItem().getItemId());
        }
        if(uc.getCollection() != null){
            dto.setCollectionId(uc.getCollection().getCollectionId());
        }
        return dto;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }
}
