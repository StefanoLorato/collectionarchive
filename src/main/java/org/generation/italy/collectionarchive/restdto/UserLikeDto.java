package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.UserLike;

public class UserLikeDto {
    private Integer likeId;
    private Integer userId;
    private Integer itemId;
    private Integer collectionId;

    public UserLikeDto() {
    }

    public UserLikeDto(Integer likeId, Integer userId, Integer itemId, Integer collectionId) {
        this.likeId = likeId;
        this.userId = userId;
        this.itemId = itemId;
        this.collectionId = collectionId;
    }

    public UserLike toUserLike(){
        UserLike ul = new UserLike(likeId, null, null, null);
        return ul;
    }

    public static UserLikeDto toDto(UserLike ul){
        UserLikeDto dto = new UserLikeDto();

        dto.setLikeId(ul.getLikeId());
        dto.setUserId(ul.getUser().getUserId());

        if(ul.getItem() != null){
            dto.setItemId(ul.getItem().getItemId());
        }
        if(ul.getCollection() != null){
            dto.setCollectionId(ul.getCollection().getCollectionId());
        }
        return dto;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user) {
        this.userId = user;
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
