package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.UserLike;

public class UserLikeDto {
    private int likeId;
    private int userId;
    private Integer itemId;
    private Integer collectionId;

    public UserLikeDto() {
    }

    public UserLikeDto(int likeId, Integer userId, Integer itemId, Integer collectionId) {
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

        dto.setUserId(ul.getUser().getUserId());

        if(ul.getItem() != null){
            dto.setItemId(ul.getItem().getItemId());
        }
        if(ul.getCollection() != null){
            dto.setCollectionId(ul.getCollection().getCollectionId());
        }
        return dto;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user) {
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
